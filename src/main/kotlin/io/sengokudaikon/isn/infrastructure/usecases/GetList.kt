package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

@Suppress("UNCHECKED_CAST")
abstract class GetList<Q : Query, R : Model, L : List<R>, S : RepositoryOutputPort<R>> :
    ReadPort<Q, L>, CachingUseCase() {
    abstract val repository: S
    override suspend fun execute(query: Q): Result<L> {
        query as Query.All<R>
        val cacheKey = getCacheKey(query)
        return runCatching {
            withCache(cacheKey) {
                repository.findAll(query.page, query.size).getOrThrow() as L
            }
        }
    }

    abstract fun getCacheKey(query: Q): String
}
