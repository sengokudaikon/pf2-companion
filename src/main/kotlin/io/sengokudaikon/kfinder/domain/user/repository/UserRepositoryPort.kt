package io.sengokudaikon.kfinder.domain.user.repository

import arrow.core.Either
import io.sengokudaikon.kfinder.domain.user.entity.User
import io.sengokudaikon.kfinder.operations.user.command.UserCommand
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface UserRepositoryPort : RepositoryCreatePort<UserCommand, User>, RepositoryOutputPort<User> {
    suspend fun update(command: UserCommand): Either<Throwable, User>
    suspend fun delete(command: UserCommand): Either<Throwable, Boolean>
    suspend fun findByEmail(email: String): Either<Throwable, User>
    suspend fun findByUid(uid: String): Either<Throwable, User>
}
