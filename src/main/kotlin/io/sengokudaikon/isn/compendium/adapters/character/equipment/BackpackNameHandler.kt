package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameBackpackPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BackpackNameHandler :
    ByNameHandler<BackpackModel, BackpackQuery.ByName, ByNameBackpackPort>() {
    override val useCase: ByNameBackpackPort by inject()
    override fun createQuery(name: String, id: String?): BackpackQuery.ByName {
        return BackpackQuery.ByName(name)
    }
}
