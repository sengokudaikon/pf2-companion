package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdWeaponPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class WeaponIdHandler :
    ByIdHandler<WeaponModel, WeaponQuery.ById, ByIdWeaponPort>() {
    override val useCase: ByIdWeaponPort by inject()
    override fun createQuery(id: String): WeaponQuery.ById {
        return WeaponQuery.ById(id)
    }
}
