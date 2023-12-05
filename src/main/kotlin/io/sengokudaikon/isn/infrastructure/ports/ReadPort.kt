package io.sengokudaikon.isn.infrastructure.ports

interface ReadPort<in Q, out R> {
    suspend fun execute(query: Q): Result<R>
}
