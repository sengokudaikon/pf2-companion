package io.sengokudaikon.ports.user.repository

import arrow.core.Either
import io.sengokudaikon.domain.user.entity.User
import io.sengokudaikon.operations.user.command.UserCommand
import io.sengokudaikon.persistence.repository.RepositoryInputPort
import io.sengokudaikon.persistence.repository.RepositoryOutputPort
import kotlinx.uuid.UUID

interface UserRepositoryPort: RepositoryInputPort<UserCommand, User>, RepositoryOutputPort<User> {
    suspend fun findByEmail(email: String): Either<Throwable, User>
    override suspend fun findById(id: UUID): Either<Throwable, User>
    suspend fun findByUid(uid: String): Either<Throwable, User>
}
