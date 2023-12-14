package io.sengokudaikon.isn.app.usecases

import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.infrastructure.usecases.CachingUseCase
import org.koin.core.annotation.Single

@Single(binds = [AuthPort::class])
class AuthUseCase(
    private val userRepository: UserRepositoryPort,
) : CachingUseCase(), AuthPort {
    override suspend fun register(command: UserCommand): Result<String> {
        return userRepository.create(command).map { it.id }
    }

    override suspend fun findUserByIdentifier(identifier: String): Result<User> =
        userRepository.findByUid(identifier)

    override suspend fun findUserByUsername(username: String): Result<Boolean> = runCatching {
        userRepository.findByUsername(username).map { true }.getOrElse { false }
    }

    override suspend fun checkIfMailExists(email: String): Result<Boolean> = runCatching {
        userRepository.findByEmail(email).map { true }.getOrElse { false }
    }
}
