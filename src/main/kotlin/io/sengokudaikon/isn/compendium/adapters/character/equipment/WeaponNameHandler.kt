package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameWeaponPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class WeaponNameHandler : ByNameHandler<WeaponModel, WeaponQuery.ByName, ByNameWeaponPort>() {
    override val useCase: ByNameWeaponPort by inject()
    override fun createQuery(name: String, id: String?): WeaponQuery.ByName {
        return WeaponQuery.ByName(name = name)
    }
}
