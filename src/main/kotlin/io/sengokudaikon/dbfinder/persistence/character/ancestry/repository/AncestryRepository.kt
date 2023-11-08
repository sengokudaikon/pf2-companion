package io.sengokudaikon.dbfinder.persistence.character.ancestry.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.Ancestry
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryBoost
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryFlaw
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryLanguage
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryTrait
import io.sengokudaikon.dbfinder.domain.character.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.dbfinder.domain.character.ancestry.repository.VisionSenseRepositoryPort
import io.sengokudaikon.dbfinder.domain.world.global.repository.LanguageRepositoryPort
import io.sengokudaikon.dbfinder.domain.world.global.repository.TraitRepositoryPort
import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.toSizeEnum
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.Ancestries
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryLanguages
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryTraits
import io.sengokudaikon.shared.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single(binds = [AncestryRepositoryPort::class])
class AncestryRepository(
    private val visionSenseRepository: VisionSenseRepositoryPort,
    private val languageRepository: LanguageRepositoryPort,
    private val traitRepositoryPort: TraitRepositoryPort,
) : AncestryRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Ancestry> =
        suspendedTransactionAsync {
            val entity = Ancestry.find { Ancestries.name eq name }.firstOrNull()
            entity?.right() ?: DatabaseException.NotFound().left()
        }.await()

    override suspend fun findById(id: UUID): Either<Throwable, Ancestry> {
        return suspendedTransactionAsync { Ancestry.findById(id) }.await()
            .let {
                it?.right() ?: DatabaseException.NotFound().left()
            }
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Ancestry>> {
        val offset = (page - 1).toLong()
        val ancList = Ancestry.all()
            .with(
                Ancestry::abilityBoosts,
                Ancestry::abilityFlaws,
                Ancestry::traits,
                Ancestry::languages,
                Ancestry::features,
                Ancestry::rules,
            )
            .limit(limit, offset)
            .toList()
        return ancList.right()
    }

    override suspend fun findAllNames(): List<String> = suspendedTransactionAsync {
        Ancestries.slice(Ancestries.name).selectAll().map { it[Ancestries.name] }
    }.await()

    override suspend fun batchInsert(models: Set<AncestryCommand>) {
        // ignored
    }

    override suspend fun create(command: AncestryCommand): Either<Throwable, Ancestry> {
        command as AncestryCommand.Create
        val dto = command.dto
        return suspendedTransactionAsync {
            val ancestry = Ancestry.new {
                img = dto.img
                name = dto.name
                description = dto.system.description
                rarity = Rarity.valueOf(dto.system.rarity.uppercase())
                hp = dto.system.hp
                size = dto.system.size.toSizeEnum()
                speed = dto.system.speed
                contentSrc = dto.system.source
                rules = dto.system.rules.toString()
            }
            ancestry.visionSense = dto.system.vision.let {
                visionSenseRepository.findByName(it).fold({ throw it }, { it })
            }
            ancestry.additionalSense = dto.system.additionalSense?.let {
                visionSenseRepository.findByName(it).fold({ throw it }, { it })
            }
            dto.system.languages.value.forEach {
                val language = languageRepository.findByName(it).fold({ throw it }, { it })
                val languages = AncestryLanguage.find {
                    AncestryLanguages.ancestryID eq ancestry.id and (AncestryLanguages.languageID eq language.id)
                }.firstOrNull()
                    ?: AncestryLanguage.new {
                        this.language = language
                        this.ancestryID = ancestry
                    }
                ancestry.languages.plus(languages)
            }

            val boosts = dto.system.boosts.forEach { (key, value) ->
                if ((value.value.size > 2) || (value.value.isEmpty())) {
                    AncestryBoost.new {
                        this.ancestryID = ancestry
                        this.boost = Ability.Anything
                    }
                } else {
                    value.value.map { boost ->
                        AncestryBoost.new {
                            this.ancestryID = ancestry
                            this.boost = Ability.valueOf(boost)
                        }
                    }
                }
            }
            ancestry.abilityBoosts.plus(boosts)
            val abilityFlaws = dto.system.flaws.forEach { (key, value) ->
                value.value.map { boost ->
                    AncestryFlaw.new {
                        this.ancestryID = ancestry
                        this.flaw = Ability.valueOf(boost)
                    }
                }
            }
            ancestry.abilityFlaws.plus(abilityFlaws)
            val traits = dto.system.traits.value.map {
                val trait = traitRepositoryPort.findByName(it).fold({ throw it }, { it })
                AncestryTrait.find { AncestryTraits.ancestryID eq ancestry.id and (AncestryTraits.trait eq trait.id) }
                    .firstOrNull() ?: AncestryTrait.new {
                    this.ancestryID = ancestry
                    this.trait = trait
                }
            }
            ancestry.traits.plus(traits)
            ancestry.flush()
            commit()
            ancestry
        }.await().right()
    }
}
