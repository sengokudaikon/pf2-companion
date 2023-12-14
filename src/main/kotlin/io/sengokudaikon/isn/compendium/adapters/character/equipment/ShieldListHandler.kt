package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ListShieldPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class ShieldListHandler(override val useCase: ListShieldPort) : ListHandler<List<ShieldModel>, ShieldQuery.All, ListShieldPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): ShieldQuery.All {
        return ShieldQuery.All(page, size)
    }
}
