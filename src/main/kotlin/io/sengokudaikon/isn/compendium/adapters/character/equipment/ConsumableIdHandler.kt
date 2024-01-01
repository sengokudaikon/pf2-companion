package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ConsumableQuery
import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.compendium.ports.character.ByIdConsumablePort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ConsumableIdHandler :
    ByIdHandler<ConsumableModel, ConsumableQuery.ById, ByIdConsumablePort>() {
    override val useCase: ByIdConsumablePort by inject()
    override fun createQuery(id: String): ConsumableQuery.ById {
        return ConsumableQuery.ById(id)
    }
}
