package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ListWeaponPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class WeaponListHandler(override val useCase: ListWeaponPort) : ListHandler<List<WeaponModel>, WeaponQuery.All, ListWeaponPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): WeaponQuery.All {
        return WeaponQuery.All(page, size)
    }
}
