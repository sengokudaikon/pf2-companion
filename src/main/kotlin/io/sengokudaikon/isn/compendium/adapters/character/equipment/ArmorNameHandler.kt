package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameArmorPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class ArmorNameHandler(override val useCase: ByNameArmorPort) : ByNameHandler<ArmorModel, ArmorQuery.ByName, ByNameArmorPort>() {
    override fun createQuery(name: String, id: String?): ArmorQuery.ByName {
        return ArmorQuery.ByName(name = name)
    }
}
