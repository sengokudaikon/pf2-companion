package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdShieldPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ShieldIdHandler :
    ByIdHandler<ShieldModel, ShieldQuery.ById, ByIdShieldPort>() {
    override val useCase: ByIdShieldPort by inject()
    override fun createQuery(id: String): ShieldQuery.ById {
        return ShieldQuery.ById(id)
    }
}
