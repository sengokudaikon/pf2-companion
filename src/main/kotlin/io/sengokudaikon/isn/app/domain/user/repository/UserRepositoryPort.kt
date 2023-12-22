package io.sengokudaikon.isn.app.domain.user.repository

import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface UserRepositoryPort : RepositoryOutputPort<User> {
    suspend fun create(command: UserCommand, uid: String): Result<User>
    suspend fun update(command: UserCommand, uid: String): Result<User>
    suspend fun delete(command: UserCommand, uid: String): Result<Boolean>
    suspend fun findByEmail(email: String): Result<User>
    suspend fun findByUid(uid: String): Result<User>
    suspend fun findByUsername(username: String): Result<User>
}
