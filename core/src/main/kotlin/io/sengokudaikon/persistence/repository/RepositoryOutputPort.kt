package io.sengokudaikon.persistence.repository

import arrow.core.Either
import io.sengokudaikon.domain.user.entity.User
import kotlinx.uuid.UUID

interface RepositoryOutputPort<T> {
    suspend fun findById(id: UUID): Either<Throwable, T>
    suspend fun findAll(): Either<Throwable, List<User>>
}
