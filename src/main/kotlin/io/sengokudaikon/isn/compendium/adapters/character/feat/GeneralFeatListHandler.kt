package io.sengokudaikon.isn.compendium.adapters.character.feat

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.operations.character.feat.query.FeatQuery
import io.sengokudaikon.isn.compendium.ports.character.ListFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class GeneralFeatListHandler : ListHandler<List<FeatModel>, FeatQuery.General.All, ListFeatPort>() {
    override val useCase: ListFeatPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, id: String?): FeatQuery.General.All {
        return FeatQuery.General.All(page, size, filters)
    }
}
