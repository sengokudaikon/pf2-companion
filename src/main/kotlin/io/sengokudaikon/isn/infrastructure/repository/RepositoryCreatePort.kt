package io.sengokudaikon.isn.infrastructure.repository

import arrow.core.Either

interface RepositoryCreatePort<C, T> {
    suspend fun batchInsert(models: Set<C>)
    suspend fun create(command: C): Either<Throwable, T>
}
