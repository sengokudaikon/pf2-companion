package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.compendium.operations.search.dto.SortType
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

@Suppress("UNCHECKED_CAST")
abstract class GetList<Q : Query, R : Model> :
    ReadPort<Q, List<R>>, Caching, Filtering, Sorting {
    abstract val repository: RepositoryOutputPort<R>
    override suspend fun execute(query: Q): Result<List<R>> {
        query as Query.All<R>
        val filterQuery = query.filters
        val filters: List<Filter> = if (filterQuery.isNullOrEmpty()) {
            emptyList()
        } else {
            parseFilterExpression(filterQuery)
        }
        val sortQuery = query.sort
        val sort: List<Sort> = if (sortQuery.isNullOrEmpty()) {
            listOf(Sort(SortType.Level, true))
        } else {
            parseSortExpression(sortQuery)
        }
        val cacheKey = getCacheKey(query)
        return runCatching {
            withCache(cacheKey) {
                repository.findAll(query.page, query.size, filters, sort).getOrThrow()
            }
        }
    }
    abstract fun getCacheKey(query: Q): String
}
