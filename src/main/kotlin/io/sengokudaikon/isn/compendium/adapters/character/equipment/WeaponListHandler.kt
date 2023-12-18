package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.WeaponQuery
import io.sengokudaikon.isn.compendium.ports.character.ListWeaponPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class WeaponListHandler : ListHandler<List<WeaponModel>, WeaponQuery.All, ListWeaponPort>() {
    override val useCase: ListWeaponPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): WeaponQuery.All {
        return WeaponQuery.All(page, size)
    }
}
