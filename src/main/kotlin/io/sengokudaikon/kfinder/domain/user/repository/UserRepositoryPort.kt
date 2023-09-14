package io.sengokudaikon.kfinder.domain.user.repository

import arrow.core.Either
import io.sengokudaikon.kfinder.domain.user.entity.User
import io.sengokudaikon.kfinder.operations.user.command.UserCommand
import io.sengokudaikon.shared.persistence.repository.RepositoryInputPort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import kotlinx.uuid.UUID

interface UserRepositoryPort : RepositoryInputPort<UserCommand, User>, RepositoryOutputPort<User> {
    suspend fun findByEmail(email: String): Either<Throwable, User>
    override suspend fun findById(id: UUID): Either<Throwable, User>
    suspend fun findByUid(uid: String): Either<Throwable, User>
    suspend fun findByUsername(username: String): Either<Throwable, User>
}
