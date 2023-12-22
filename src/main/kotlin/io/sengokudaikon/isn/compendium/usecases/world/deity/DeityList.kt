package io.sengokudaikon.isn.compendium.usecases.world.deity

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.domain.deity.repository.DeityRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.compendium.ports.world.ListDeityPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListDeityPort::class])
class DeityList(override val repository: DeityRepositoryPort) :
    GetList<DeityQuery, DeityModel>(), ListDeityPort {
    override fun getCacheKey(query: DeityQuery): String {
        query as DeityQuery.All
        return "model_deity:all:${query.page}:${query.size}"
    }
}