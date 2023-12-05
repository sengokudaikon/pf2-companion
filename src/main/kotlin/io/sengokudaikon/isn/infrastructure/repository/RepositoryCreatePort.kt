package io.sengokudaikon.isn.infrastructure.repository

interface RepositoryCreatePort<in C, out T> {
    suspend fun create(command: C): Result<T>
}
