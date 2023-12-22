package io.sengokudaikon.isn.compendium.adapters.world.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdActionPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ActionIdHandler : ByIdHandler<ActionModel, ActionQuery.ById, ByIdActionPort>() {
    override val useCase: ByIdActionPort by inject()
    override fun createQuery(id: String, secondaryId: String?): ActionQuery.ById {
        return ActionQuery.ById(id)
    }
}
