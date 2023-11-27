package io.sengokudaikon.isn.infrastructure.ports

import arrow.core.Either

interface ReadPort<Q, R> {
    suspend fun get(query: Q): Either<Throwable, R>
    suspend fun list(query: Q): Either<Throwable, List<R>>
    suspend fun getByName(query: Q): Either<Throwable, R>
}
