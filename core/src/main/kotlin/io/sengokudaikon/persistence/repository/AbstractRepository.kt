package io.sengokudaikon.persistence.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.infrastructure.errors.DatabaseException
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

abstract class AbstractRepository {
    protected suspend fun <T> query(block: () -> T?): Either<Throwable, T> =
        newSuspendedTransaction(Dispatchers.IO) {
            runCatching {
                val result = block()
                result?.right() ?: DatabaseException.NotFound().left()
            }.getOrElse {
                it.left()
            }
        }
}
