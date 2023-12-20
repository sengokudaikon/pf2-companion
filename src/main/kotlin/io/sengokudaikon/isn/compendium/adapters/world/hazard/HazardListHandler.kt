package io.sengokudaikon.isn.compendium.adapters.world.hazard

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.compendium.ports.world.ListHazardPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class HazardListHandler :
    ListHandler<List<HazardModel>, HazardQuery.All, ListHazardPort>() {
    override val useCase: ListHazardPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): HazardQuery.All {
        return HazardQuery.All(page, size)
    }
}
