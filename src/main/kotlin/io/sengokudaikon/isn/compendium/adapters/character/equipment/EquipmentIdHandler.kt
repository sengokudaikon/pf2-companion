package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdEquipmentPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class EquipmentIdHandler :
    ByIdHandler<EquipmentModel, EquipmentQuery.ById, ByIdEquipmentPort>() {
    override val useCase: ByIdEquipmentPort by inject()
    override fun createQuery(id: String): EquipmentQuery.ById {
        return EquipmentQuery.ById(id)
    }
}
