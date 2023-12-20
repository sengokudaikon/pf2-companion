package io.sengokudaikon.isn.compendium.adapters.world.deity

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.compendium.operations.world.deity.query.DeityQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdDeityPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class DeityIdHandler :
    ByIdHandler<DeityModel, DeityQuery.ById, ByIdDeityPort>() {
    override val useCase: ByIdDeityPort by inject()
    override fun createQuery(id: String, secondaryId: String?): DeityQuery.ById {
        return DeityQuery.ById(id)
    }
}
