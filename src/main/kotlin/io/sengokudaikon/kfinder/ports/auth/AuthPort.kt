package io.sengokudaikon.kfinder.ports.auth

import arrow.core.Either
import io.sengokudaikon.kfinder.domain.user.entity.User
import io.sengokudaikon.kfinder.domain.user.model.UserToken
import io.sengokudaikon.kfinder.operations.user.UserIdentifier
import io.sengokudaikon.kfinder.operations.user.command.UserCommand

interface AuthPort {
    suspend fun register(command: UserCommand): Either<Throwable, String>
    suspend fun findUserByIdentifier(identifier: UserIdentifier): Either<Throwable, User>
    suspend fun authenticate(command: UserCommand): Either<Throwable, UserToken>
    suspend fun checkIfExists(identifier: UserIdentifier): Boolean
    suspend fun checkIfMailExists(email: String): Boolean
}
