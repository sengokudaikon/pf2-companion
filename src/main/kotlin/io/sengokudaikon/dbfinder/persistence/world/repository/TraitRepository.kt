package io.sengokudaikon.dbfinder.persistence.world.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.world.entity.Trait
import io.sengokudaikon.dbfinder.domain.world.model.toTraitType
import io.sengokudaikon.dbfinder.domain.world.repository.TraitRepositoryPort
import io.sengokudaikon.dbfinder.fixtures.TraitFixture
import io.sengokudaikon.dbfinder.persistence.world.entity.Traits
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import io.sengokudaikon.dbfinder.domain.world.model.Trait as ModelTrait

@Single(binds = [TraitRepositoryPort::class])
class TraitRepository : TraitRepositoryPort {
    override suspend fun batchInsert(models: Set<TraitFixture>) {
        val traitSet = models.map { trait ->
            ModelTrait(
                name = trait.name,
                description = trait.description,
                contentSrc = trait.contentSrc,
                type = trait.type.toTraitType(),
            )
        }.toSet()
        batchInsertModels(traitSet)
    }

    override suspend fun create(command: TraitFixture): Either<Throwable, Trait> {
        return suspendedTransactionAsync {
            Trait.new {
                name = command.name
                description = command.description
                contentSrc = command.contentSrc
                type = command.type.toTraitType()
            }
        }.await().right()
    }

    override suspend fun findByName(name: String): Either<Throwable, Trait> {
        return suspendedTransactionAsync {
            val entity = Trait.find { Traits.name eq name }.firstOrNull()
            entity?.right() ?: DatabaseException.NotFound("Trait not found").left()
        }.await()
    }

    override suspend fun findById(id: UUID): Either<Throwable, Trait> {
        return suspendedTransactionAsync {
            val entity = Trait.findById(id)
            entity?.right() ?: DatabaseException.NotFound("Trait not found").left()
        }.await()
    }

    override suspend fun findAllNames(): List<String> = suspendedTransactionAsync {
        Traits.slice(Traits.name).selectAll().map { it[Traits.name] }
    }.await()

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Trait>> {
        val offset = (page - 1).toLong()
        return suspendedTransactionAsync {
            Trait.all().limit(limit, offset).toList()
        }.await().right()
    }

    suspend fun batchInsertModels(traitSet: Set<ModelTrait>) {
        suspendedTransactionAsync {
            Traits.batchInsert(traitSet) { trait ->
                this[Traits.name] = trait.name
                this[Traits.description] = trait.description
                this[Traits.contentSrc] = trait.contentSrc
                this[Traits.type] = trait.type
            }
        }.await()
    }
}
