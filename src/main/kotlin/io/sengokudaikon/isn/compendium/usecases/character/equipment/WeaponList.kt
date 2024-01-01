package io.sengokudaikon.isn.compendium.usecases.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.domain.equipment.repository.WeaponRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ListWeaponPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListWeaponPort::class])
class WeaponList(override val repository: WeaponRepositoryPort) :
    GetList<WeaponQuery, WeaponModel>(), ListWeaponPort {
    override fun getCacheKey(query: WeaponQuery): String {
        query as WeaponQuery.All
        return "model_weapon:all:${query.page}:${query.size}"
    }
}
