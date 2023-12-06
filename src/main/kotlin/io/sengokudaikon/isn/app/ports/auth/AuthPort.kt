package io.sengokudaikon.isn.app.ports.auth

import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.operations.user.command.UserCommand

interface AuthPort {
    suspend fun register(command: UserCommand.Create): Result<String>
    suspend fun findUserByIdentifier(identifier: String): Result<User>
    suspend fun authenticate(command: UserCommand.SignIn): Result<User>
    suspend fun checkIfExists(identifier: String): Boolean
    suspend fun checkIfMailExists(email: String): Boolean
}
