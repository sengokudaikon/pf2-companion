package io.sengokudaikon.kfinder.persistence.user.repository

import arrow.core.Either
import arrow.core.left
import io.sengokudaikon.kfinder.domain.user.entity.User
import io.sengokudaikon.kfinder.domain.user.entity.UserToken
import io.sengokudaikon.kfinder.domain.user.repository.UserTokenRepositoryPort
import io.sengokudaikon.kfinder.infrastructure.errors.UserException
import io.sengokudaikon.kfinder.persistence.user.entity.UserTokens
import io.sengokudaikon.kfinder.persistence.user.entity.Users
import io.sengokudaikon.shared.persistence.repository.AbstractRepository
import kotlinx.uuid.UUID
import org.koin.core.annotation.Single
import io.sengokudaikon.kfinder.domain.user.model.UserToken as Model

@Single
class UserTokenRepository : AbstractRepository(), UserTokenRepositoryPort {
    private suspend fun findUser(userId: UUID): Either<Throwable, User?> {
        return query {
            User.find { Users.id eq userId }.firstOrNull()
        }
    }

    private suspend fun saveNewToken(model: Model): Either<Throwable, UserToken> {
        return query {
            UserToken.new {
                userId = model.userId
                token = model.token
            }
        }
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
        return query {
            UserToken.find { UserTokens.token eq token }.firstOrNull()
        }
    }
}
