package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameEquipmentPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class EquipmentNameHandler(override val useCase: ByNameEquipmentPort) : ByNameHandler<EquipmentModel, EquipmentQuery.ByName, ByNameEquipmentPort>() {
    override fun createQuery(name: String, id: String?): EquipmentQuery.ByName {
        return EquipmentQuery.ByName(name = name)
    }
}
