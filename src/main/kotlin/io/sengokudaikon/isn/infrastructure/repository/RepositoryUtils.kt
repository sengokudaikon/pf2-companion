package io.sengokudaikon.isn.infrastructure.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

suspend inline fun <reified T> executeFindOperation(
    param: Any,
    crossinline findOperation: suspend () -> T?,
): Either<Throwable, T> {
    return suspendedTransactionAsync {
        val entity = findOperation()
        val typeName = T::class.simpleName?.replaceFirstChar { it.uppercase() }
        entity?.right()
            ?: DatabaseException.NotFound("$typeName '$param' not found").left()
    }.await()
}
