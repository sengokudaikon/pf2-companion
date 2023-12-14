package io.sengokudaikon.isn.compendium.adapters.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryFeatsQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameAncestryFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryFeatNameHandler :
    ByNameHandler<AncestryFeatureModel, AncestryFeatsQuery.ByName, ByNameAncestryFeatPort>() {
    override val useCase: ByNameAncestryFeatPort by inject()
    override fun createQuery(name: String, id: String?): AncestryFeatsQuery.ByName {
        return AncestryFeatsQuery.ByName(id!!, name)
    }
}
