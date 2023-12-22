package io.sengokudaikon.isn.compendium.usecases.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdAncestryPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdAncestryPort::class])
class AncestryById(
    override val repository: AncestryRepositoryPort,
) : GetById<AncestryQuery, AncestryModel>(), ByIdAncestryPort {
    override fun getCacheKey(query: AncestryQuery): String {
        query as AncestryQuery.ById
        return "model_ancestry:id:${query.id}"
    }
}
