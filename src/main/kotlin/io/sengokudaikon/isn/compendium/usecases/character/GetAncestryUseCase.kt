package io.sengokudaikon.isn.compendium.usecases.character

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.GetAncestryPort
import io.sengokudaikon.isn.compendium.usecases.CachingUseCase
import org.koin.core.annotation.Single

@Single(binds = [GetAncestryPort::class])
class GetAncestryUseCase(
    private val ancestryRepository: AncestryRepositoryPort,
) : GetAncestryPort, CachingUseCase() {
    override suspend fun execute(query: AncestryQuery): Result<AncestryModel> {
        query as AncestryQuery.FindById
        val cacheKey = "model_ancestry_id:${query.id}"
        return runCatching {
            withCache(cacheKey) {
                ancestryRepository.findById(query.id).getOrThrow()
            }
        }
    }
}
