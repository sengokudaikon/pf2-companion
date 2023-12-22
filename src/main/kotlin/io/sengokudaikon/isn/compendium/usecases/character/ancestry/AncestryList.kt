package io.sengokudaikon.isn.compendium.usecases.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ListAncestryPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListAncestryPort::class])
class AncestryList(
    override val repository: AncestryRepositoryPort,
) : GetList<AncestryQuery, AncestryModel>(), ListAncestryPort {
    override fun getCacheKey(query: AncestryQuery): String {
        query as AncestryQuery.All
        return "model_ancestry:all:${query.page}:${query.size}"
    }
}
