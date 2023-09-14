package io.sengokudaikon.shared.ports

import arrow.core.Either

interface CrudPort<C, Q, R> {
    suspend fun create(command: C): Either<Throwable, R>
    suspend fun update(command: C): Either<Throwable, R>
    suspend fun delete(command: C): Either<Throwable, Boolean>
    suspend fun get(query: Q): Either<Throwable, R>
    suspend fun list(query: Q): Either<Throwable, List<R>>
}
