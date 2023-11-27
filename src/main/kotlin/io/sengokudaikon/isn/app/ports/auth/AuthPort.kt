package io.sengokudaikon.isn.app.ports.auth

import arrow.core.Either
import io.sengokudaikon.isn.app.domain.user.model.User
import io.sengokudaikon.isn.app.operations.user.command.UserCommand

interface AuthPort {
    suspend fun register(command: UserCommand): Either<Throwable, String>
    suspend fun findUserByIdentifier(identifier: String): Either<Throwable, User>
    suspend fun authenticate(command: UserCommand): Either<Throwable, User>
    suspend fun checkIfExists(identifier: String): Boolean
    suspend fun checkIfMailExists(email: String): Boolean
}
