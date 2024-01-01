package io.sengokudaikon.isn.compendium.adapters.character.equipment

import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.query.BackpackQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdBackpackPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class BackpackIdHandler :
    ByIdHandler<BackpackModel, BackpackQuery.ById, ByIdBackpackPort>() {
    override val useCase: ByIdBackpackPort by inject()
    override fun createQuery(id: String): BackpackQuery.ById {
        return BackpackQuery.ById(id)
    }
}
