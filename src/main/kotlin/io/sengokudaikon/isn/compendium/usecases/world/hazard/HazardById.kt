package io.sengokudaikon.isn.compendium.usecases.world.hazard

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.domain.hazard.repository.HazardRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdHazardPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdHazardPort::class])
class HazardById(override val repository: HazardRepositoryPort) :
    GetById<HazardQuery, HazardModel>(), ByIdHazardPort {
    override fun getCacheKey(query: HazardQuery): String {
        query as HazardQuery.ById
        return "model_hazard:id:${query.id}"
    }
}