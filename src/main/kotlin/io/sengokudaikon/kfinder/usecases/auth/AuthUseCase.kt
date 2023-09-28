package io.sengokudaikon.kfinder.usecases.auth

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.kfinder.domain.user.model.UserToken
import io.sengokudaikon.kfinder.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.kfinder.infrastructure.Argon2PasswordEncoder
import io.sengokudaikon.kfinder.infrastructure.JwtService
import io.sengokudaikon.kfinder.infrastructure.errors.UserException
import io.sengokudaikon.kfinder.operations.user.UserIdentifier
import io.sengokudaikon.kfinder.operations.user.command.UserCommand
import io.sengokudaikon.kfinder.ports.auth.AuthPort
import io.sengokudaikon.kfinder.domain.user.entity.User as EntityUser

class AuthUseCase(
    private val userRepository: UserRepositoryPort,
    private val jwtConfig: JwtService,
    private val encoder: Argon2PasswordEncoder,
) : AuthPort {
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

    override suspend fun findUserByIdentifier(identifier: UserIdentifier): Either<Throwable, EntityUser> =
        when (identifier) {
            is UserIdentifier.Email -> userRepository.findByEmail(identifier.email)
            is UserIdentifier.Uid -> userRepository.findByUid(identifier.uid)
            is UserIdentifier.Username -> userRepository.findByName(identifier.username)
        }

    override suspend fun authenticate(command: UserCommand): Either<Exception, UserToken> {
        command as UserCommand.SignIn
        val user = findUserByIdentifier(command.identifier).getOrElse { return Either.Left(UserException.NotFound()) }

        require(encoder.verify(command.password, user.password)) { UserException.Invalid("Invalid password") }
        val token = jwtConfig.generateToken(user.id.value.toString())
        return UserToken(
            token = token,
            userId = user.toModel().id,
        ).right()
    }

    override suspend fun checkIfExists(identifier: UserIdentifier): Boolean =
        findUserByIdentifier(identifier).map { true }.getOrElse { false }

    override suspend fun checkIfMailExists(email: String): Boolean =
        userRepository.findByEmail(email).map { true }.getOrElse { false }
}
