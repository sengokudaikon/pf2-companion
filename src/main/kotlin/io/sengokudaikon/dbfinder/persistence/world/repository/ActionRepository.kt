package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.world.action.entity.Action
import io.sengokudaikon.dbfinder.domain.world.action.entity.ActionEffect
import io.sengokudaikon.dbfinder.domain.world.action.entity.ActionTrait
import io.sengokudaikon.dbfinder.domain.world.action.repository.ActionRepositoryPort
import io.sengokudaikon.dbfinder.domain.world.effect.repository.EffectRepositoryPort
import io.sengokudaikon.dbfinder.domain.world.global.repository.TraitRepositoryPort
import io.sengokudaikon.dbfinder.infrastructure.enums.ActionCategories
import io.sengokudaikon.dbfinder.infrastructure.enums.ActionTypes
import io.sengokudaikon.dbfinder.persistence.world.entity.Actions
import io.sengokudaikon.fixtureloader.fixtures.model.ActionFixture
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single(binds = [ActionRepositoryPort::class])
class ActionRepository(
    private val effectRepositoryPort: EffectRepositoryPort,
    private val traitRepositoryPort: TraitRepositoryPort,
) : ActionRepositoryPort {
    override suspend fun batchInsert(models: Set<ActionFixture>) {
        // ignored
    }

    override suspend fun create(command: ActionFixture): Either<Throwable, Action> {
        return suspendedTransactionAsync {
            val action = Action.new {
                name = command.name
                description = command.system.description.value
                actionType = command.system.actionType.value.let {
                    if (it == "") {
                        return@let ActionTypes.NONE
                    } else {
                        return@let ActionTypes.valueOf(it.uppercase())
                    }
                }
                frequency = command.system.frequency
                    .let { "max ${command.system.frequency.max} per ${command.system.frequency.per}" }
                trigger = command.system.trigger.value
                requirements = command.system.requirements.value
                actionNumber = command.system.actions?.value
                contentSrc = command.system.publication.title
                rules = command.system.rules.toString()
                category = command.system.category.let {
                    if (it == "") {
                        return@let ActionCategories.NONE
                    } else {
                        return@let ActionCategories.valueOf(it.uppercase())
                    }
                }
                image = command.img
            }
            if (command.system.selfEffect != null) {
                val effect = effectRepositoryPort.findByName(command.system.selfEffect.name).getOrElse { throw it }
                val actionEffect = ActionEffect.new {
                    this.actionId = action
                    this.effect = effect
                }
                action.effects.plus(actionEffect)
            }
            val traits = command.system.traits.value.map {
                val trait = traitRepositoryPort.findByName(it).getOrElse { err -> throw err }
                ActionTrait.new {
                    this.actionID = action
                    this.trait = trait
                }
            }
            action.traits.plus(traits)
            action
        }.await().right()
    }

    override suspend fun findByName(name: String): Either<Throwable, Action> {
        return suspendedTransactionAsync { Action.find { Actions.name eq name }.firstOrNull() }.await().let {
            it?.right() ?: Either.Left(Throwable("Action '$name' not found"))
        }
    }

    override suspend fun findById(id: UUID): Either<Throwable, Action> {
        return suspendedTransactionAsync { Action.findById(id) }.await().let {
            it?.right() ?: Either.Left(Throwable("Action '$id' not found"))
        }
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Action>> {
        return suspendedTransactionAsync { Action.all().limit(limit, (page - 1).toLong()).toList() }.await().right()
    }

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync { Actions.slice(Actions.name).selectAll().map { it[Actions.name] } }.await()
    }
}
