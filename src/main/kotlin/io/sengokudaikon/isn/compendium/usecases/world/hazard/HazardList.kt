package io.sengokudaikon.isn.compendium.usecases.world.hazard

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.compendium.domain.hazard.repository.HazardRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.hazard.query.HazardQuery
import io.sengokudaikon.isn.compendium.ports.world.ListHazardPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListHazardPort::class])
class HazardList(override val repository: HazardRepositoryPort) :
    GetList<HazardQuery, HazardModel>(), ListHazardPort {
    override fun getCacheKey(query: HazardQuery): String {
        query as HazardQuery.All
        return "model_hazard:all:${query.page}:${query.size}"
    }
}