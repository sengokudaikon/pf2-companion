package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.world.effect.entity.DurationUnit
import io.sengokudaikon.dbfinder.domain.world.effect.entity.Effect
import io.sengokudaikon.dbfinder.domain.world.effect.entity.EffectDuration
import io.sengokudaikon.dbfinder.domain.world.effect.repository.EffectRepositoryPort
import io.sengokudaikon.dbfinder.persistence.world.entity.Effects
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import io.sengokudaikon.dbfinder.domain.world.effect.model.Effect as ModelEffect

@Single(binds = [EffectRepositoryPort::class])
class EffectsRepository : EffectRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Effect> {
        return suspendedTransactionAsync { Effect.find { Effects.name eq name }.firstOrNull() }.await().let {
            it?.right() ?: Either.Left(Throwable("Effect '$name' not found"))
        }
    }

    override suspend fun findById(id: UUID): Either<Throwable, Effect> {
        return suspendedTransactionAsync { Effect.findById(id) }.await().let {
            it?.right() ?: Either.Left(Throwable("Effect '$id' not found"))
        }
    }

    override suspend fun findAll(
        page: Int,
        limit: Int,
    ): Either<Throwable, List<Effect>> {
        return suspendedTransactionAsync {
            Effect.all().limit(limit, ((page - 1) * limit).toLong()).toList()
        }.await().right()
    }

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync { Effect.all().map { it.name } }.await()
    }

    override suspend fun batchInsert(models: Set<ModelEffect>) {
        // ignored
    }

    override suspend fun create(command: ModelEffect): Either<Throwable, Effect> {
        return suspendedTransactionAsync {
            val effect = Effect.new {
                img = command.img
                name = command.name
                description = command.description
                level = command.level
                contentSrc = command.contentSrc
                rules = command.rules.toString()
                rarity = command.rarity
            }
            if (command.duration.isNotEmpty()) {
                val duration = command.duration.map {
                    EffectDuration.new {
                        this.effect = effect
                        this.expiry = it.expiry
                        this.sustained = it.sustained
                        this.unit = DurationUnit.valueOf(it.unit)
                        this.value = it.value
                    }
                }
                effect.duration.plus(duration)
            }
            effect
        }.await().right()
    }
}
