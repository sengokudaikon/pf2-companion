package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

abstract class GetByName<Q : Query, R : Model, S : RepositoryOutputPort<R>> : ReadPort<Q, R>, CachingUseCase() {
    abstract val repository: S
    override suspend fun execute(query: Q): Result<R> {
        query as Query.ByName<R>
        val cacheKey = getCacheKey(query)
        return runCatching {
            withCache(cacheKey) {
                repository.findByName(query.name).getOrThrow()
            }
        }
    }
    abstract fun getCacheKey(query: Q): String
}
