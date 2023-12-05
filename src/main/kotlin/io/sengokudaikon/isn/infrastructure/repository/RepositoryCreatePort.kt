package io.sengokudaikon.isn.infrastructure.repository

import arrow.core.Either

interface RepositoryCreatePort<in C, out T> {
    suspend fun create(command: C): Either<Throwable, T>
}
