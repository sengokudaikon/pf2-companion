package io.sengokudaikon.isn.compendium.usecases.world.hazard

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.domain.hazard.repository.HazardRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameHazardPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameHazardPort::class])
class HazardByName(override val repository: HazardRepositoryPort) :
    GetByName<HazardQuery, HazardModel>(), ByNameHazardPort {
    override fun getCacheKey(query: HazardQuery): String {
        query as HazardQuery.ByName
        return "model_hazard:name:${query.name}"
    }
}