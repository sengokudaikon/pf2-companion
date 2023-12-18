package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.ShieldQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameShieldPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ShieldNameHandler : ByNameHandler<ShieldModel, ShieldQuery.ByName, ByNameShieldPort>() {
    override val useCase: ByNameShieldPort by inject()
    override fun createQuery(name: String, id: String?): ShieldQuery.ByName {
        return ShieldQuery.ByName(name = name)
    }
}
