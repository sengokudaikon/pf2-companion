package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.WeaponRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdWeaponPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdWeaponPort::class])
class WeaponById(override val repository: WeaponRepositoryPort) :
    GetById<WeaponQuery, WeaponModel>(), ByIdWeaponPort {
    override fun getCacheKey(query: WeaponQuery): String {
        query as WeaponQuery.ById
        return "model_weaponmodel:id:${query.id}"
    }
}
