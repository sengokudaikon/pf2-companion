package io.sengokudaikon.dbfinder.persistence.character.background.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.background.entity.Background
import io.sengokudaikon.dbfinder.domain.character.background.entity.BackgroundBoost
import io.sengokudaikon.dbfinder.domain.character.background.entity.BackgroundItem
import io.sengokudaikon.dbfinder.domain.character.background.entity.BackgroundRule
import io.sengokudaikon.dbfinder.domain.character.background.entity.BackgroundSkill
import io.sengokudaikon.dbfinder.domain.character.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.dbfinder.domain.character.feat.entity.Feat
import io.sengokudaikon.dbfinder.domain.world.entity.Action
import io.sengokudaikon.dbfinder.domain.world.entity.Rule
import io.sengokudaikon.dbfinder.domain.world.entity.Spell
import io.sengokudaikon.dbfinder.fixtures.BackgroundFixture
import io.sengokudaikon.dbfinder.persistence.character.background.entity.Backgrounds
import io.sengokudaikon.dbfinder.persistence.world.entity.Actions
import io.sengokudaikon.dbfinder.persistence.world.entity.Feats
import io.sengokudaikon.dbfinder.persistence.world.entity.Rules
import io.sengokudaikon.dbfinder.persistence.world.entity.Spells
import io.sengokudaikon.dbfinder.persistence.world.repository.RuleRepository
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single(binds = [BackgroundRepositoryPort::class])
class BackgroundRepository(
    private val ruleRepository: RuleRepository,
) : BackgroundRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Background> {
        return suspendedTransactionAsync {
            val entity = Background.find { Backgrounds.name eq name }.firstOrNull()
            entity?.right() ?: DatabaseException.NotFound("Background not found").left()
        }.await()
    }

    override suspend fun findById(id: UUID): Either<Throwable, Background> {
        return suspendedTransactionAsync {
            val entity = Background.findById(id)
            entity?.right() ?: DatabaseException.NotFound("Background not found").left()
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
                description = command.description
                rarity = command.rarity
                trainedLore = command.trainedLore
                contentSrc = command.source
            }
            val boosts = mutableListOf<BackgroundBoost>()
            command.boosts.map { boost ->
                boost.values().forEach {
                    boosts.add(
                        BackgroundBoost.new {
                            this.backgroundId = entity
                            this.boostedAbility = it
                        },
                    )
                }
            }

            val featItems = command.items.filter {
                it.uuid.startsWith("Feat.")
            }.map {
                val name = it.name ?: it.uuid.replace("Feat.", "")
                val feat = Feat.find { Feats.name eq name }.firstOrNull()
                    ?: throw DatabaseException.NotFound("Feat not found")
                BackgroundItem.new { background = entity; this.feat = feat }
            }
            val actionItems = command.items.filter {
                it.uuid.startsWith("Action.")
            }.map {
                val name = it.name ?: it.uuid.replace("Action.", "")
                val action = Action.find { Actions.name eq name }.firstOrNull()
                    ?: throw DatabaseException.NotFound("Action not found")
                BackgroundItem.new { background = entity; this.action = action }
            }
            val spellItems = command.items.filter {
                it.uuid.startsWith("Spell.")
            }.map {
                val name = it.name ?: it.uuid.replace("Spell.", "")
                val spell = Spell.find { Spells.name eq name }.firstOrNull()
                    ?: throw DatabaseException.NotFound("Spell not found")
                BackgroundItem.new { background = entity; this.spell = spell }
            }

            val rules = command.rules.map { rules ->
                val rule = Rule.find { Rules.name eq rules.key }.firstOrNull()
                    ?: ruleRepository.create(rules.toModel()).fold({ throw it }, { it })
                BackgroundRule.new {
                    background = entity
                    this.rule = rule
                }
            }

            val skills = command.trainedSkills.map { skill ->
                skill.values().forEach {
                    BackgroundSkill.new {
                        this.backgroundId = entity
                        this.skillId = it
                    }
                }
            }
            entity.trainedSkills.plus(skills)
            entity.rules.plus(rules)
            entity.items.plus(featItems).plus(actionItems).plus(spellItems)
            entity.boosts.plus(boosts)
            entity.right()
        }.await()
    }
}
