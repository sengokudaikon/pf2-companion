package io.sengokudaikon.usecases.auth

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.domain.user.model.User
import io.sengokudaikon.infrastructure.JwtService
import io.sengokudaikon.infrastructure.errors.UserException
import io.sengokudaikon.operations.user.command.UserCommand
import io.sengokudaikon.ports.auth.AuthPort
import io.sengokudaikon.ports.user.repository.UserRepositoryPort

class AuthUseCase(
    private val userRepository: UserRepositoryPort,
    private val jwtConfig: JwtService,
): AuthPort {
    override suspend fun register(command: UserCommand): Either<Throwable, String> {
        val user = userRepository.create(command)
            .mapLeft { it }
            .map { it }
        return if (user.isRight()) {
            val uid = user.getOrNull()?.username ?: throw UserException.Invalid("Error during registration")
            jwtConfig.generateToken(uid).right()
        } else {
            user.leftOrNull()?.left() ?: UserException.Invalid("Error during registration").left()
        }
    }

    override suspend fun authenticate(uid: String?, command: UserCommand): Either<Exception, User> {
        TODO("Not yet implemented")
    }

    override suspend fun checkIfExists(uid: String?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun checkIfMailExists(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByUid(uid: String): Either<Exception, User> {
        TODO("Not yet implemented")
    }
}
