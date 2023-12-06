package io.sengokudaikon.isn.compendium.usecases.character

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.GetByNameAncestryPort
import io.sengokudaikon.isn.compendium.usecases.CachingUseCase
import org.koin.core.annotation.Single

@Single(binds = [GetByNameAncestryPort::class])
class GetByNameAncestryUseCase(
    private val ancestryRepository: AncestryRepositoryPort,
) : GetByNameAncestryPort, CachingUseCase() {

    override suspend fun execute(query: AncestryQuery): Result<AncestryModel> {
        query as AncestryQuery.FindByName
        val cacheKey = "model_ancestry:name:${query.name}"
        return runCatching {
            withCache(cacheKey) {
                ancestryRepository.findByName(query.name).getOrThrow()
            }
        }
    }
}
