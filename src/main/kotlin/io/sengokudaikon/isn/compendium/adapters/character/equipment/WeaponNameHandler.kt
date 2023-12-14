package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameWeaponPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class WeaponNameHandler(override val useCase: ByNameWeaponPort) : ByNameHandler<WeaponModel, WeaponQuery.ByName, ByNameWeaponPort>() {
    override fun createQuery(name: String, id: String?): WeaponQuery.ByName {
        return WeaponQuery.ByName(name = name)
    }
}
