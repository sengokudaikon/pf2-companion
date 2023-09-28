package io.sengokudaikon.dbfinder.persistence.character.ancestry.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.Ancestry
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryBoost
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryFlaw
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryLanguage
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryPhysicalFeature
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryRule
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.AncestryTrait
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.VisionSense
import io.sengokudaikon.dbfinder.domain.character.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.dbfinder.domain.world.entity.Language
import io.sengokudaikon.dbfinder.domain.world.entity.Rule
import io.sengokudaikon.dbfinder.domain.world.entity.RuleChoice
import io.sengokudaikon.dbfinder.domain.world.entity.Trait
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.Ancestries
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryBoosts
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryFlaws
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryLanguages
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryPhysicalFeatures
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryTraits
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.VisionSenses
import io.sengokudaikon.dbfinder.persistence.world.entity.Languages
import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import io.sengokudaikon.dbfinder.persistence.world.entity.Traits
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single(binds = [AncestryRepositoryPort::class])
class AncestryRepository : AncestryRepositoryPort {
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
                Ancestry::physicalFeatures,
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
        suspendedTransactionAsync {
            Ancestries.batchInsert(models) { ancestry ->
                ancestry as AncestryCommand.Create
                val dto = ancestry.dto
                this[Ancestries.name] = dto.name
                this[Ancestries.description] = dto.description
                this[Ancestries.rarity] = dto.rarity
                this[Ancestries.hitPoints] = dto.hp
                this[Ancestries.size] = dto.size
                this[Ancestries.speed] = dto.speed
                this[Ancestries.img] = dto.img
                this[Ancestries.contentSrc] = dto.source
            }
        }.await()
    }

    override suspend fun create(command: AncestryCommand): Either<Throwable, Ancestry> {
        command as AncestryCommand.Create
        val dto = command.dto
        return suspendedTransactionAsync {
            val ancestry = Ancestry.new {
                img = dto.img
                name = dto.name
                description = dto.description
                rarity = dto.rarity
                hp = dto.hp
                size = dto.size
                speed = dto.speed
                contentSrc = dto.source
                homebrewID = null
                version = 1.toString()
                visionSense = dto.vision.let {
                    VisionSense.find { VisionSenses.name eq it.name }.firstOrNull() ?: VisionSense.new {
                        this.name = it.name
                        this.visionRange = it.range
                    }
                }
                additionalSense = dto.additionalSense?.let {
                    VisionSense.find { VisionSenses.name eq it.name }.firstOrNull() ?: VisionSense.new {
                        this.name = it.name
                        this.visionRange = it.range
                    }
                }
            }
            dto.languages.forEach {
                val language = Language.find { Languages.name eq it.name }.firstOrNull() ?: Language.new {
                    this.name = it.name
                    this.description = it.description
                }
                val languages = AncestryLanguage.find {
                    AncestryLanguages.ancestryID eq ancestry.id and (AncestryLanguages.languageID eq language.id)
                }.firstOrNull()
                    ?: AncestryLanguage.new {
                        this.language = language
                        this.ancestryID = ancestry
                    }
                ancestry.languages.plus(languages)
            }

            val boosts = dto.boosts.map {
                AncestryBoost.find {
                    AncestryBoosts.ancestryID eq ancestry.id and (AncestryBoosts.boostedAbility eq it)
                }
                    .firstOrNull() ?: AncestryBoost.new {
                    this.ancestryID = ancestry
                    this.boost = it
                }
            }
            ancestry.abilityBoosts.plus(boosts)
            val abilityFlaws = dto.flaws.map {
                AncestryFlaw.find { AncestryFlaws.ancestryID eq ancestry.id and (AncestryFlaws.flawedAbility eq it) }
                    .firstOrNull() ?: AncestryFlaw.new {
                    this.ancestryID = ancestry
                    flaw = it
                }
            }
            ancestry.abilityFlaws.plus(abilityFlaws)
            val traits = dto.traits.map {
                val trait = Trait.find { Traits.name eq it.name }.firstOrNull() ?: Trait.new {
                    name = it.name
                    description = it.description
                    contentSrc = it.contentSrc
                    type = it.type
                }
                AncestryTrait.find { AncestryTraits.ancestryID eq ancestry.id and (AncestryTraits.trait eq trait.id) }
                    .firstOrNull() ?: AncestryTrait.new {
                    this.ancestryID = ancestry
                    this.trait = trait
                }
            }
            ancestry.traits.plus(traits)
            val physicalFeatures = dto.physicalFeatures.map {
                AncestryPhysicalFeature.find {
                    AncestryPhysicalFeatures.ancestryID eq ancestry.id and (AncestryPhysicalFeatures.name eq it.name)
                }
                    .firstOrNull() ?: AncestryPhysicalFeature.new {
                    this.ancestry = ancestry
                    description = it.description
                    img = it.img
                    level = it.level
                    name = it.name
                }
            }
            ancestry.physicalFeatures.plus(physicalFeatures)
            if (dto.rules.isEmpty()) {
                ancestry.flush()
                commit()
                return@suspendedTransactionAsync ancestry
            }

            val rules = dto.rules.map {
                val rule = Rule.find { Rules.name eq it.name }.firstOrNull() ?: Rule.new {
                    this.name = it.name
                    this.mode = it.mode
                    this.priority = it.priority
                    this.prompt = it.prompt
                    this.description = "Refer to the ${ancestry.name} ancestry rules for more information."
                }
                for ((key, choice) in it.ruleChoices) {
                    val ruleChoice = RuleChoice.new {
                        this.ruleId = rule
                        this.name = key
                        this.value = choice
                    }
                    rule.ruleChoices.plus(ruleChoice)
                }
                AncestryRule.new {
                    this.ancestryID = ancestry
                    this.ruleID = rule
                }
            }
            ancestry.rules.plus(rules)
            ancestry.flush()
            commit()
            ancestry
        }.await().right()
    }
}
