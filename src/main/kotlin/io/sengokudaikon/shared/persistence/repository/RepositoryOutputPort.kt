package io.sengokudaikon.shared.persistence.repository

import arrow.core.Either
import kotlinx.uuid.UUID

interface RepositoryOutputPort<T> {
    suspend fun findByName(name: String): Either<Throwable, T>
    suspend fun findById(id: UUID): Either<Throwable, T>
    suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<T>>
    suspend fun findAllNames(): List<String>
}
