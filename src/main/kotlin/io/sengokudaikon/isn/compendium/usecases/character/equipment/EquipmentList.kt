package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.EquipmentRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ListEquipmentPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListEquipmentPort::class])
class EquipmentList(override val repository: EquipmentRepositoryPort) :
    GetList<EquipmentQuery, EquipmentModel, List<EquipmentModel>, EquipmentRepositoryPort>(), ListEquipmentPort {
    override fun getCacheKey(query: EquipmentQuery): String {
        query as EquipmentQuery.All
        return "model_equipment:all:${query.page}:${query.size}"
    }
}
