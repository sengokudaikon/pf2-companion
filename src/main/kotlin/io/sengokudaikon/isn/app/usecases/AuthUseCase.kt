package io.sengokudaikon.isn.app.usecases

import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.infrastructure.usecases.Caching
import org.koin.core.annotation.Single

@Single(binds = [AuthPort::class])
class AuthUseCase(
    private val userRepository: UserRepositoryPort,
) : Caching, AuthPort {
    override suspend fun register(command: UserCommand, uid: String): Result<String> {
        return userRepository.create(command, uid).map { it.id }
    }

    override suspend fun findUserByIdentifier(identifier: String): Result<User> =
        userRepository.findByUid(identifier)

    override suspend fun findUserByUsername(username: String): Result<User> =
        userRepository.findByName(username)

    override suspend fun checkIfMailExists(email: String): Result<Boolean> = runCatching {
        userRepository.findByEmail(email).map { true }.getOrElse { false }
    }

    override suspend fun checkIfUidExists(uid: String): Result<Boolean> = runCatching {
        userRepository.findByUid(uid).map { true }.getOrElse { false }
    }

    override suspend fun checkIfUsernameExists(username: String): Result<Boolean> = runCatching{
        userRepository.findByName(username).map { true }.getOrElse { false }
    }
}
