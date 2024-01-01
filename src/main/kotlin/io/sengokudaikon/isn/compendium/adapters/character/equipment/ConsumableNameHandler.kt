package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ConsumableQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameConsumablePort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ConsumableNameHandler :
    ByNameHandler<ConsumableModel, ConsumableQuery.ByName, ByNameConsumablePort>() {
    override val useCase: ByNameConsumablePort by inject()
    override fun createQuery(name: String, id: String?): ConsumableQuery.ByName {
        return ConsumableQuery.ByName(name)
    }
}
