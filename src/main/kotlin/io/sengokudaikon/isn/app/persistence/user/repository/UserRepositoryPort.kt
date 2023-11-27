package io.sengokudaikon.isn.app.persistence.user.repository

import arrow.core.Either
import io.sengokudaikon.isn.app.domain.user.entity.User
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface UserRepositoryPort : RepositoryCreatePort<UserCommand, User>, RepositoryOutputPort<User> {
    suspend fun update(command: UserCommand): Either<Throwable, User>
    suspend fun delete(command: UserCommand): Either<Throwable, Boolean>
    suspend fun findByEmail(email: String): Either<Throwable, User>
    suspend fun findByUid(uid: String): Either<Throwable, User>
}
