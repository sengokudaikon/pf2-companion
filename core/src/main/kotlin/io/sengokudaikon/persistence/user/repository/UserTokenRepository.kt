package io.sengokudaikon.persistence.user.repository

import arrow.core.Either
import arrow.core.left
import io.sengokudaikon.domain.user.entity.User
import io.sengokudaikon.domain.user.entity.UserToken
import io.sengokudaikon.infrastructure.errors.UserException
import io.sengokudaikon.persistence.repository.AbstractRepository
import io.sengokudaikon.persistence.user.entity.Users
import io.sengokudaikon.ports.user.repository.UserTokenRepositoryPort
import kotlinx.uuid.UUID
import io.sengokudaikon.domain.user.model.UserToken as Model

class UserTokenRepository:AbstractRepository(), UserTokenRepositoryPort {
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
}
