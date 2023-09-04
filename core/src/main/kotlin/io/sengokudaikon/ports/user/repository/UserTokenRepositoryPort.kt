package io.sengokudaikon.ports.user.repository

import arrow.core.Either
import io.sengokudaikon.domain.user.entity.UserToken as Entity
import io.sengokudaikon.domain.user.model.UserToken as Model

interface UserTokenRepositoryPort {
    suspend fun saveToken(model: Model): Either<Throwable, Entity>
}
