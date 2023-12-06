package io.sengokudaikon.isn.compendium.usecases.character

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ListAncestryPort
import io.sengokudaikon.isn.compendium.usecases.CachingUseCase
import org.koin.core.annotation.Single

@Single(binds = [ListAncestryPort::class])
class ListAncestryUseCase(
    private val ancestryRepository: AncestryRepositoryPort,
) : ListAncestryPort, CachingUseCase() {
    override suspend fun execute(query: AncestryQuery): Result<List<AncestryModel>> {
        query as AncestryQuery.FindAll
        val cacheKey = "model_ancestry:all:${query.page}:${query.limit}"
        return runCatching {
            withCache(cacheKey) {
                ancestryRepository.findAll(query.page, query.limit).getOrThrow()
            }
        }
    }
}
