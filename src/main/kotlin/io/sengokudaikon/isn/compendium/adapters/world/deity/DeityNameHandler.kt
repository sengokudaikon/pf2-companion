package io.sengokudaikon.isn.compendium.adapters.world.deity

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameDeityPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class DeityNameHandler :
    ByNameHandler<DeityModel, DeityQuery.ByName, ByNameDeityPort>() {
    override val useCase: ByNameDeityPort by inject()
    override fun createQuery(name: String, id: String?): DeityQuery.ByName {
        return DeityQuery.ByName(name)
    }
}
