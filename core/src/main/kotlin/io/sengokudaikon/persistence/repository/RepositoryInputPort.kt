package io.sengokudaikon.persistence.repository

import arrow.core.Either
import io.sengokudaikon.domain.user.entity.User

interface RepositoryInputPort<C, T> {
    suspend fun create(command: C): Either<Throwable, User>
    suspend fun update(command: C): Either<Throwable, T>
    suspend fun delete(command: C): Either<Throwable, Boolean>
}
