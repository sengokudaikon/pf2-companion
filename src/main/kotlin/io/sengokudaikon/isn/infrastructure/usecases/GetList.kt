package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

@Suppress("UNCHECKED_CAST")
abstract class GetList<Q : Query, R : Model> :
    ReadPort<Q, List<R>>, Caching {
    abstract val repository: RepositoryOutputPort<R>
    override suspend fun execute(query: Q): Result<List<R>> {
        query as Query.All<R>
        val cacheKey = getCacheKey(query)
        return runCatching {
            withCache(cacheKey) {
                repository.findAll(query.page, query.size, Criteria().fromFilterString(query.filters).fromSortString(query.sort)).getOrThrow()
            }
        }
    }
    abstract fun getCacheKey(query: Q): String
}
