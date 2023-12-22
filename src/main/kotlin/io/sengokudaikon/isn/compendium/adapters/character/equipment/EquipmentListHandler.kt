package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ListEquipmentPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class EquipmentListHandler : ListHandler<EquipmentModel, EquipmentQuery.All, ListEquipmentPort>() {
    override val useCase: ListEquipmentPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String?): EquipmentQuery.All {
        return EquipmentQuery.All(page, size).apply {
            this.filters = filters
        this.sort = sort
        }
    }
}
