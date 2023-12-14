package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.WeaponRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameWeaponPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class WeaponByName(override val repository: WeaponRepositoryPort) :
    GetByName<WeaponQuery, WeaponModel, WeaponRepositoryPort>(), ByNameWeaponPort {
    override fun getCacheKey(query: WeaponQuery): String {
        query as WeaponQuery.ByName
        return "model_weapon:name:${query.name}"
    }
}
