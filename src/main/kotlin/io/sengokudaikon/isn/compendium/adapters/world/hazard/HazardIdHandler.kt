package io.sengokudaikon.isn.compendium.adapters.world.hazard

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdHazardPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class HazardIdHandler :
    ByIdHandler<HazardModel, HazardQuery.ById, ByIdHazardPort>() {
    override val useCase: ByIdHazardPort by inject()
    override fun createQuery(id: String): HazardQuery.ById {
        return HazardQuery.ById(id)
    }
}
