package io.sengokudaikon.isn.compendium.adapters.world.deity

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.compendium.ports.world.ListDeityPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class DeityListHandler :
    ListHandler<DeityModel, DeityQuery.All, ListDeityPort>() {
    override val useCase: ListDeityPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): DeityQuery.All {
        return DeityQuery.All(page, size).apply {
            this.filters = filters
            this.sort = sort
        }
    }
}
