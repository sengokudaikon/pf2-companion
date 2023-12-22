package io.sengokudaikon.isn.compendium.usecases.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryFeatsQuery
import io.sengokudaikon.isn.compendium.ports.character.ListAncestryFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListAncestryFeatPort::class])
class AncestryFeatList(
    override val repository: AncestryFeatureRepositoryPort,
) : ListAncestryFeatPort,
    GetList<AncestryFeatsQuery, AncestryFeatureModel>() {
    override fun getCacheKey(query: AncestryFeatsQuery): String {
        query as AncestryFeatsQuery.All
        return "model_ancestry_feat:all:${query.page}:${query.size}"
    }
}
