package io.sengokudaikon.isn.infrastructure.ports

interface CrudPort<C, Q, R> : ReadPort<Q, R> {
    suspend fun create(command: C): Result<R>
    suspend fun update(command: C): Result<R>
    suspend fun delete(command: C): Result<Boolean>
}
