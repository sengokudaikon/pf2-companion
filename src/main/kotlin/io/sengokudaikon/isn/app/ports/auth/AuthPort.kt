package io.sengokudaikon.isn.app.ports.auth

import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.operations.user.command.UserCommand

interface AuthPort {
    suspend fun register(command: UserCommand, uid: String): Result<String>
    suspend fun findUserByIdentifier(identifier: String): Result<User>
    suspend fun findUserByUsername(username: String): Result<User>
    suspend fun checkIfMailExists(email: String): Result<Boolean>
    suspend fun checkIfUsernameExists(username: String): Result<Boolean>
    suspend fun checkIfUidExists(uid: String): Result<Boolean>
}
