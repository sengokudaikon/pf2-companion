package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

abstract class GetById<Q : Query, R : Model> : ReadPort<Q, R>, Caching {
    abstract val repository: RepositoryOutputPort<R>

    @Suppress("UNCHECKED_CAST")
    override suspend fun execute(query: Q): Result<R> {
        query as Query.ById<R>
        val cacheKey = getCacheKey(query)
        return runCatching {
            withCache(cacheKey) {
                repository.findById(query.id, Criteria()).getOrThrow()
            }
        }
    }

    abstract fun getCacheKey(query: Q): String
}
