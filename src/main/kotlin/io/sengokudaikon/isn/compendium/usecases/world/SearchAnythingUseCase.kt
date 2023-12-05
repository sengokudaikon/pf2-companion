package io.sengokudaikon.isn.compendium.usecases.world

import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
import io.sengokudaikon.isn.compendium.persistence.world.SearchRepository
import io.sengokudaikon.isn.compendium.ports.world.SearchPort
import org.koin.core.annotation.Single

@Single(binds = [SearchPort::class])
class SearchAnythingUseCase(
    private val repository: SearchRepository
) : SearchPort {
    override suspend fun execute(query: SearchPort.Query): Result<List<SearchResult>> {
        return runCatching {
            query.query?.let { repository.searchAnything(it) } ?: emptyList()
        }
    }
}
