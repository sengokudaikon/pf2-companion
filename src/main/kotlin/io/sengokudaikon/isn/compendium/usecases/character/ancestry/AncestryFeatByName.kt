package io.sengokudaikon.isn.compendium.usecases.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryFeatsQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameAncestryFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameAncestryFeatPort::class])
class AncestryFeatByName(
    override val repository: AncestryFeatureRepositoryPort,
) : ByNameAncestryFeatPort, GetByName<AncestryFeatsQuery, AncestryFeatureModel, AncestryFeatureRepositoryPort>() {
    override fun getCacheKey(query: AncestryFeatsQuery): String {
        query as AncestryFeatsQuery.ByName
        return "model_ancestry_feat:name:${query.name}"
    }
}
