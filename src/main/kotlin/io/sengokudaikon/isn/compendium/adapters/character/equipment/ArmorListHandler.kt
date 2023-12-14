package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ArmorQuery
import io.sengokudaikon.isn.compendium.ports.character.ListArmorPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class ArmorListHandler(override val useCase: ListArmorPort) : ListHandler<List<ArmorModel>, ArmorQuery.All, ListArmorPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): ArmorQuery.All {
        return ArmorQuery.All(page, size)
    }
}
