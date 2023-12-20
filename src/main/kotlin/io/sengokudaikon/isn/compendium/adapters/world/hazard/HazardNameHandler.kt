package io.sengokudaikon.isn.compendium.adapters.world.hazard

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameHazardPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class HazardNameHandler :
    ByNameHandler<HazardModel, HazardQuery.ByName, ByNameHazardPort>() {
    override val useCase: ByNameHazardPort by inject()
    override fun createQuery(name: String, id: String?): HazardQuery.ByName {
        return HazardQuery.ByName(name)
    }
}
