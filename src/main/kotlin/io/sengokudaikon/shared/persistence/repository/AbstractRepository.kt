package io.sengokudaikon.shared.persistence.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

abstract class AbstractRepository {
    protected suspend fun <T> query(block: () -> T?): Either<Throwable, T> =
        suspendedTransactionAsync(Dispatchers.IO) {
            val result = runCatching {
                val result = block()
                result?.right() ?: DatabaseException.NotFound().left()
            }.getOrElse {
                it.left()
            }
            result
        }.await()
}
