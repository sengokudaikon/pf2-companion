package io.sengokudaikon.isn.compendium.usecases.world

import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.compendium.persistence.world.SearchRepository
import io.sengokudaikon.isn.compendium.ports.world.SearchPort
import io.sengokudaikon.isn.infrastructure.usecases.Filtering
import io.sengokudaikon.isn.infrastructure.usecases.Sorting
import org.koin.core.annotation.Single

@Single(binds = [SearchPort::class])
class SearchAnythingUseCase(
    private val repository: SearchRepository
) : SearchPort, Filtering, Sorting {
    override suspend fun execute(query: SearchQuery): Result<List<SearchResult>> {
        return runCatching {
            val filterQuery = query.filters
            val filters: List<Filter> = if (filterQuery.isNullOrEmpty()) {
                emptyList()
            } else {
                parseFilterExpression(filterQuery)
            }
            val sortQuery = query.sort
            val sort: List<Sort> = if (sortQuery.isNullOrEmpty()) {
                emptyList()
            } else {
                parseSortExpression(sortQuery)
            }
            query.let { repository.searchAnything(it, filters, sort) }
        }
    }
}
