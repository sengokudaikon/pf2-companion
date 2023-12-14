package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameShieldPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class ShieldNameHandler(override val useCase: ByNameShieldPort) : ByNameHandler<ShieldModel, ShieldQuery.ByName, ByNameShieldPort>() {
    override fun createQuery(name: String, id: String?): ShieldQuery.ByName {
        return ShieldQuery.ByName(name = name)
    }
}
