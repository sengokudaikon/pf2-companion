package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ListFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class GeneralFeatListHandler(override val useCase: ListFeatPort) : ListHandler<List<FeatModel>, FeatQuery.General.All, ListFeatPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): FeatQuery.General.All {
        return FeatQuery.General.All(page, size)
    }
}
