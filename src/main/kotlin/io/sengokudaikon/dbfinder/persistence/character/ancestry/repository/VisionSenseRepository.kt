package io.sengokudaikon.dbfinder.persistence.character.ancestry.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.VisionSense
import io.sengokudaikon.dbfinder.domain.character.ancestry.repository.VisionSenseRepositoryPort
import io.sengokudaikon.dbfinder.infrastructure.enums.VisionType
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.VisionSenses
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.VisionSense as ModelVisionSense

@Single(binds = [VisionSenseRepositoryPort::class])
class VisionSenseRepository : VisionSenseRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, VisionSense> {
        return suspendedTransactionAsync {
            val entity = VisionSense.find { VisionSenses.name eq VisionType.valueOf(name) }.firstOrNull()
            entity?.right() ?: DatabaseException.NotFound("Vision sense not found").left()
        }.await()
    }

    override suspend fun findById(id: UUID): Either<Throwable, VisionSense> {
        return suspendedTransactionAsync {
            val entity = VisionSense.findById(id)
            entity?.right() ?: DatabaseException.NotFound("Vision sense not found").left()
        }.await()
    }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<VisionSense>> {
        val offset = (page - 1).toLong()
        val list = suspendedTransactionAsync { VisionSense.all().limit(limit, offset).toList() }.await()
        return list.right()
    }

    override suspend fun findAllNames(): List<String> = suspendedTransactionAsync {
        VisionSenses.slice(VisionSenses.name).selectAll().map { it[VisionSenses.name].name }
    }.await()

    override suspend fun batchInsert(models: Set<ModelVisionSense>) {
        suspendedTransactionAsync {
            VisionSenses.batchInsert(models) { visionSense ->
                this[VisionSenses.name] = visionSense.name
                this[VisionSenses.visionRange] = visionSense.range
                this[VisionSenses.precision] =
                    if (visionSense.isPrecise) VisionSenses.Precision.PRECISE else VisionSenses.Precision.IMPRECISE
            }
        }.await()
    }

    override suspend fun create(command: ModelVisionSense): Either<Throwable, VisionSense> {
        return suspendedTransactionAsync {
            VisionSense.new {
                name = command.name
                visionRange = command.range
                precision = if (command.isPrecise) VisionSenses.Precision.PRECISE else VisionSenses.Precision.IMPRECISE
            }
        }.await().right()
    }
}
