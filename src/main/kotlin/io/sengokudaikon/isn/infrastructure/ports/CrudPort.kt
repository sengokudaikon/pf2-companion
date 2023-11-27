package io.sengokudaikon.isn.infrastructure.ports

import arrow.core.Either

interface CrudPort<C, Q, R> : ReadPort<Q, R> {
    suspend fun create(command: C): Either<Throwable, R>
    suspend fun update(command: C): Either<Throwable, R>
    suspend fun delete(command: C): Either<Throwable, Boolean>
}
