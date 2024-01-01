package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ConsumableQuery
import io.sengokudaikon.isn.compendium.ports.character.ListConsumablePort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ConsumableListHandler : ListHandler<ConsumableModel, ConsumableQuery.All, ListConsumablePort>() {
    override val useCase: ListConsumablePort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): ConsumableQuery.All {
        return ConsumableQuery.All(page, size).apply {
            this.filters = filters
        this.sort = sort
        }
    }
}
