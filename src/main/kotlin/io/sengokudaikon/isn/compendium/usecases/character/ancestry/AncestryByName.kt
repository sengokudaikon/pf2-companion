package io.sengokudaikon.isn.compendium.usecases.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameAncestryPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameAncestryPort::class])
class AncestryByName(
    override val repository: AncestryRepositoryPort,
) : ByNameAncestryPort, GetByName<AncestryQuery, AncestryModel, AncestryRepositoryPort>() {
    override fun getCacheKey(query: AncestryQuery): String {
        query as AncestryQuery.ByName
        return "model_ancestry:name:${query.name}"
    }
}
