package io.sengokudaikon.isn.compendium.usecases.world.deity

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.domain.deity.repository.DeityRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameDeityPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameDeityPort::class])
class DeityByName(override val repository: DeityRepositoryPort) :
    GetByName<DeityQuery, DeityModel>(), ByNameDeityPort {
    override fun getCacheKey(query: DeityQuery): String {
        query as DeityQuery.ByName
        return "model_deity:name:${query.name}"
    }
}