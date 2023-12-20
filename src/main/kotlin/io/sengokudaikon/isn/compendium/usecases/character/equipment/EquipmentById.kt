package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.EquipmentRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdEquipmentPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdEquipmentPort::class])
class EquipmentById(override val repository: EquipmentRepositoryPort) :
    GetById<EquipmentQuery, EquipmentModel, EquipmentRepositoryPort>(),
    ByIdEquipmentPort {
    override fun getCacheKey(query: EquipmentQuery): String {
        query as EquipmentQuery.ById
        return "model_equipment:id:${query.id}"
    }
}
