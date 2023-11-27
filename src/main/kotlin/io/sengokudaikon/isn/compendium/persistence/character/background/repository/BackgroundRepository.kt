package io.sengokudaikon.isn.compendium.persistence.character.background.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.isn.builder.fixtures.model.BackgroundFixture
import io.sengokudaikon.isn.compendium.domain.character.background.entity.Background
import io.sengokudaikon.isn.compendium.domain.character.background.entity.BackgroundBoost
import io.sengokudaikon.isn.compendium.domain.character.background.entity.BackgroundItem
import io.sengokudaikon.isn.compendium.domain.character.background.entity.BackgroundSkill
import io.sengokudaikon.isn.compendium.domain.character.background.entity.BackgroundTrait
import io.sengokudaikon.isn.compendium.domain.character.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.compendium.domain.character.feat.repository.FeatRepositoryPort
import io.sengokudaikon.isn.compendium.domain.world.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.domain.world.global.repository.TraitRepositoryPort
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.enums.Rarity
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.persistence.character.background.entity.Backgrounds
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single(binds = [BackgroundRepositoryPort::class])
class BackgroundRepository(
    private val featRepository: FeatRepositoryPort,
    private val actionRepository: ActionRepositoryPort,
    private val traitRepository: TraitRepositoryPort,
) : BackgroundRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Background> {
        return suspendedTransactionAsync {
            val entity = Background.find { Backgrounds.name eq name }.firstOrNull()
            entity?.right() ?: DatabaseException.NotFound("Background '$name' not found").left()
        }.await()
    }

    override suspend fun findById(id: UUID): Either<Throwable, Background> {
        return suspendedTransactionAsync {
            val entity = Background.findById(id)
            entity?.right() ?: DatabaseException.NotFound("Background '$id' not found").left()
        }.await()
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Background>> {
        val offset = (page - 1).toLong()
        val list = Background.all().limit(limit, offset).toList()
        return list.right()
    }

    override suspend fun findAllNames(): List<String> = suspendedTransactionAsync {
        Backgrounds.slice(Backgrounds.name).selectAll().map { it[Backgrounds.name] }
    }.await()

    override suspend fun batchInsert(models: Set<BackgroundFixture>) {
        // ignored
    }

    override suspend fun create(command: BackgroundFixture): Either<Nothing, Background> {
        return suspendedTransactionAsync {
            val entity = Background.new {
                name = command.name
                description = command.system.description.toString()
                rarity = Rarity.valueOf(command.system.traits.rarity)
                trainedLore = command.system.trainedLore
                contentSrc = command.system.publication.title
                rules = command.system.rules.toString()
            }
            val boosts = mutableListOf<BackgroundBoost>()
            command.system.boosts.forEach { (_, value) ->
                if ((value.value.size > 2) || (value.value.isEmpty())) {
                    BackgroundBoost.new {
                        this.backgroundId = entity
                        this.boostedAbility = Ability.Anything
                    }
                } else {
                    value.value.map { boost ->
                        BackgroundBoost.new {
                            this.backgroundId = entity
                            this.boostedAbility = Ability.valueOf(boost)
                        }
                    }
                }
            }
            entity.boosts.plus(boosts)
            val skills = command.system.trainedSkills.value.map { skill ->
                BackgroundSkill.new {
                    this.backgroundId = entity
                    this.skillId = Skills.valueOf(skill)
                }
            }
            entity.trainedSkills.plus(skills)
            val featItems = command.system.items.filter {
                it.value.uuid.startsWith("Feat.")
            }.map {
                val name = it.value.name ?: it.value.uuid.replace("Feat.", "")
                val feat = featRepository.findByName(name).fold({ throw it }, { it })
                BackgroundItem.new { background = entity; this.feat = feat }
            }
            val actionItems = command.system.items.filter {
                it.value.uuid.startsWith("Action.")
            }.map {
                val name = it.value.name ?: it.value.uuid.replace("Action.", "")
                val action = actionRepository.findByName(name).fold({ throw it }, { it })
                BackgroundItem.new { background = entity; this.action = action }
            }
            val traits = command.system.traits.value.map {
                val trait = traitRepository.findByName(it).fold({ throw it }, { it })
                BackgroundTrait.new {
                    this.backgroundId = entity
                    this.traitId = trait
                }
            }
            entity.traits.plus(traits)
            entity.items.plus(featItems).plus(actionItems)
            entity.right()
        }.await()
    }
}
