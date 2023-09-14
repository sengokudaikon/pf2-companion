package io.sengokudaikon.kfinder.domain.user.repository

import arrow.core.Either
import io.sengokudaikon.kfinder.domain.user.entity.UserToken as Entity
import io.sengokudaikon.kfinder.domain.user.model.UserToken as Model

interface UserTokenRepositoryPort {
    suspend fun saveToken(model: Model): Either<Throwable, Entity>
    suspend fun get(token: String): Either<Throwable, Entity>
}
