package io.sengokudaikon.dbfinder.persistence.character.companion.repository

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.companion.entity.Familiar
import io.sengokudaikon.dbfinder.domain.character.companion.repository.FamiliarRepositoryPort
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.Familiars
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class FamiliarRepository : FamiliarRepositoryPort {
    override suspend fun findByName(name: String): Either<Throwable, Familiar> =
        suspendedTransactionAsync { Familiar.find { Familiars.name eq name }.first() }.await().right()

    override suspend fun findById(id: UUID): Either<Throwable, Familiar> =
        suspendedTransactionAsync { Familiar.findById(id) }.await()?.right()
            ?: DatabaseException.NotFound("Familiar not found").left()

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Familiar>> =
        suspendedTransactionAsync { Familiar.all().limit(limit, (page - 1).toLong()).toList() }.await().right()

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync {
            Familiars.slice(Familiars.name).selectAll().map { it[Familiars.name] }
        }.await().right().getOrElse { emptyList() }
    }
}
