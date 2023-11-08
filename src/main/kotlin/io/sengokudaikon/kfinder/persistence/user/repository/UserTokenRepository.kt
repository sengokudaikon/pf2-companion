package io.sengokudaikon.kfinder.persistence.user.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.kfinder.domain.user.entity.User
import io.sengokudaikon.kfinder.domain.user.entity.UserToken
import io.sengokudaikon.kfinder.domain.user.repository.UserTokenRepositoryPort
import io.sengokudaikon.kfinder.persistence.user.entity.UserTokens
import io.sengokudaikon.kfinder.persistence.user.entity.Users
import io.sengokudaikon.shared.infrastructure.errors.DatabaseException
import io.sengokudaikon.shared.infrastructure.errors.UserException
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single
import io.sengokudaikon.kfinder.domain.user.model.UserToken as Model

@Single
class UserTokenRepository : UserTokenRepositoryPort {
    private suspend fun findUser(userId: UUID): Either<Throwable, User?> {
        return suspendedTransactionAsync {
            User.find { Users.id eq userId }.firstOrNull()
        }.await()?.right() ?: DatabaseException.NotFound("User not found").left()
    }

    private suspend fun saveNewToken(model: Model): Either<Throwable, UserToken> {
        return suspendedTransactionAsync {
            UserToken.new {
                userId = model.userId
                token = model.token
            }
        }.await().right()
    }

    override suspend fun saveToken(model: Model): Either<Throwable, UserToken> {
        val user = findUser(UUID(model.userId))
        return if (user.isLeft()) {
            UserException.NotFound().left()
        } else {
            saveNewToken(model)
        }
    }

    override suspend fun get(token: String): Either<Throwable, UserToken> {
        return suspendedTransactionAsync {
            UserToken.find { UserTokens.token eq token }.firstOrNull()
        }.await()?.right() ?: DatabaseException.NotFound("Token not found").left()
    }
}
