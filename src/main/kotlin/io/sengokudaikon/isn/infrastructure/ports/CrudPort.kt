package io.sengokudaikon.isn.infrastructure.ports

import io.sengokudaikon.isn.infrastructure.operations.Query

interface CrudPort<C, Q : Query, R> : ReadPort<Q, R> {
    suspend fun create(command: C): Result<R>
    suspend fun update(command: C): Result<R>
    suspend fun delete(command: C): Result<Boolean>
}
