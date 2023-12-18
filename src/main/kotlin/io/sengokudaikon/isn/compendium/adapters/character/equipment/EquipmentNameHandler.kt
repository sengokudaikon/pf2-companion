package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.EquipmentQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameEquipmentPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class EquipmentNameHandler : ByNameHandler<EquipmentModel, EquipmentQuery.ByName, ByNameEquipmentPort>() {
    override val useCase: ByNameEquipmentPort by inject()
    override fun createQuery(name: String, id: String?): EquipmentQuery.ByName {
        return EquipmentQuery.ByName(name = name)
    }
}
