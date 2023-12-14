package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ListEquipmentPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class EquipmentListHandler(override val useCase: ListEquipmentPort) : ListHandler<List<EquipmentModel>, EquipmentQuery.All, ListEquipmentPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): EquipmentQuery.All {
        return EquipmentQuery.All(page, size)
    }
}
