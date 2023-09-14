package io.sengokudaikon.shared.persistence.repository

import arrow.core.Either

interface RepositoryInputPort<C, T> {
    suspend fun create(command: C): Either<Throwable, T>
    suspend fun update(command: C): Either<Throwable, T>
    suspend fun delete(command: C): Either<Throwable, Boolean>
}
