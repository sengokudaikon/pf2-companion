package io.sengokudaikon.isn.app.domain.user.repository

import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface UserRepositoryPort : RepositoryCreatePort<UserCommand, User>, RepositoryOutputPort<User> {
    suspend fun update(command: UserCommand): Result<User>
    suspend fun delete(command: UserCommand): Result<Boolean>
    suspend fun findByEmail(email: String): Result<User>
    suspend fun findByUid(uid: String): Result<User>
}
