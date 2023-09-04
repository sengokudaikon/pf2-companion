package io.sengokudaikon.ports.auth

import arrow.core.Either
import io.sengokudaikon.domain.user.model.User
import io.sengokudaikon.operations.user.command.UserCommand

interface AuthPort {
    suspend fun register(command: UserCommand): Either<Throwable, String>
    suspend fun authenticate(uid: String?, command: UserCommand): Either<Throwable, User>
    suspend fun checkIfExists(uid: String?): Boolean
    suspend fun checkIfMailExists(email: String): Boolean
    suspend fun getUserByUid(uid: String): Either<Throwable, User>
}
