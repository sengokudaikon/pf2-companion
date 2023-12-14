package io.sengokudaikon.isn.compendium.adapters.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryFeatsQuery
import io.sengokudaikon.isn.compendium.ports.character.ListAncestryFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryFeatListHandler :
    ListHandler<List<AncestryFeatureModel>, AncestryFeatsQuery.All, ListAncestryFeatPort>() {
    override val useCase: ListAncestryFeatPort by inject()

    override fun createQuery(page: Int, size: Int, id: String?): AncestryFeatsQuery.All {
        return AncestryFeatsQuery.All(id!!, page, size)
    }
}
