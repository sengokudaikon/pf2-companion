package io.sengokudaikon.shared.persistence.repository

import arrow.core.Either

interface RepositoryCreatePort<C, T> {
    suspend fun batchInsert(models: Set<C>)
    suspend fun create(command: C): Either<Throwable, T>
}
