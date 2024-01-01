package io.sengokudaikon.isn.compendium.usecases.world

import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.compendium.persistence.world.SearchRepository
import io.sengokudaikon.isn.compendium.ports.world.SearchPort
import io.sengokudaikon.isn.infrastructure.repository.Criteria
import org.koin.core.annotation.Single

@Single(binds = [SearchPort::class])
class SearchAnythingUseCase(
    private val repository: SearchRepository
) : SearchPort {
    override suspend fun execute(query: SearchQuery): Result<List<SearchResult>> {
        return runCatching {
            val filterQuery = query.filters
            val sortQuery = query.sort
            query.let { repository.searchAnything(it, Criteria().fromFilterString(filterQuery).fromSortString(sortQuery)) }
        }
    }
}
