package io.sengokudaikon.dbfinder.persistence.character.classs.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.classs.entity.Class
import io.sengokudaikon.dbfinder.domain.character.classs.repository.ClassRepositoryPort
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.Classes
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single
class ClassRepository : ClassRepositoryPort {
    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<Class>> =
        suspendedTransactionAsync { Class.all().limit(limit, (page - 1).toLong()).toList() }.await().right()

    override suspend fun findAllNames(): List<String> {
        return suspendedTransactionAsync { Classes.slice(Classes.name).selectAll().map { it[Classes.name] } }.await()
    }

    override suspend fun findByName(name: String): Either<Throwable, Class> =
        suspendedTransactionAsync { Class.find { Classes.name eq name }.first() }.await().right()

    override suspend fun findById(id: UUID): Either<Throwable, Class> =
        suspendedTransactionAsync { Class.findById(id) }.await()?.right()
            ?: DatabaseException.NotFound("Class not found").left()
}
