package io.sengokudaikon.isn.app.usecases

import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.compendium.usecases.CachingUseCase

class AuthUseCase(
    private val userRepository: UserRepositoryPort,
) : CachingUseCase(), AuthPort {
    override suspend fun register(command: UserCommand): Result<String> {
        return userRepository.create(command).map { it.id }
    }

    override suspend fun findUserByIdentifier(identifier: String): Result<User> =
        userRepository.findByUid(identifier)

    override suspend fun authenticate(command: UserCommand): Result<User> {
        command as UserCommand.SignIn
        return findUserByIdentifier(command.uid)
    }

    override suspend fun checkIfExists(identifier: String): Boolean =
        findUserByIdentifier(identifier).map { true }.getOrElse { false }

    override suspend fun checkIfMailExists(email: String): Boolean =
        userRepository.findByEmail(email).map { true }.getOrElse { false }
}
