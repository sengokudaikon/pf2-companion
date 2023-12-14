package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.EquipmentRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameEquipmentPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class EquipmentByName(override val repository: EquipmentRepositoryPort) :
    GetByName<EquipmentQuery, EquipmentModel, EquipmentRepositoryPort>(), ByNameEquipmentPort {
    override fun getCacheKey(query: EquipmentQuery): String {
        query as EquipmentQuery.ByName
        return "model_equipment:name:${query.name}"
    }
}
