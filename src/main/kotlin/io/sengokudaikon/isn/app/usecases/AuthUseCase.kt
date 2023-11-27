package io.sengokudaikon.isn.app.usecases

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.isn.app.domain.user.model.User
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.persistence.user.repository.UserRepositoryPort
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.infrastructure.errors.UserException

class AuthUseCase(
    private val userRepository: UserRepositoryPort,
) : AuthPort {
    override suspend fun register(command: UserCommand): Either<Throwable, String> {
        return userRepository.create(command)
            .fold(
                ifLeft = { it.left() },
                ifRight = { it.id.value.toString().right() },
            )
    }

    override suspend fun findUserByIdentifier(identifier: String): Either<Throwable, User> =
        userRepository.findByUid(identifier).fold(
            ifLeft = { UserException.NotFound().left() },
            ifRight = { it.toModel().right() },
        )

    override suspend fun authenticate(command: UserCommand): Either<Exception, User> {
        command as UserCommand.SignIn
        val user = findUserByIdentifier(command.uid).getOrElse { return Either.Left(UserException.NotFound()) }

        return user.right()
    }

    override suspend fun checkIfExists(identifier: String): Boolean =
        findUserByIdentifier(identifier).map { true }.getOrElse { false }

    override suspend fun checkIfMailExists(email: String): Boolean =
        userRepository.findByEmail(email).map { true }.getOrElse { false }
}