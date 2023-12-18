package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ListShieldPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ShieldListHandler : ListHandler<List<ShieldModel>, ShieldQuery.All, ListShieldPort>() {
    override val useCase: ListShieldPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): ShieldQuery.All {
        return ShieldQuery.All(page, size)
    }
}
