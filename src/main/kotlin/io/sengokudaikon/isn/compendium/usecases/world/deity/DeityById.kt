package io.sengokudaikon.isn.compendium.usecases.world.deity

import io.sengokudaikon.isn.compendium.domain.deity.repository.DeityRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.ports.world.ByIdDeityPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdDeityPort::class])
class DeityById(override val repository: DeityRepositoryPort) : GetById<DeityQuery, DeityModel>(),
    ByIdDeityPort {
    override fun getCacheKey(query: DeityQuery): String {
        query as DeityQuery.ById
        return "model_deity:id:${query.id}"
    }
}