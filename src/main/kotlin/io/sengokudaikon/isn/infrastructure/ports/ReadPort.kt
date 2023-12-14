package io.sengokudaikon.isn.infrastructure.ports

import io.sengokudaikon.isn.infrastructure.operations.Query

interface ReadPort<in Q : Query, out R> {
    suspend fun execute(query: Q): Result<R>
}
