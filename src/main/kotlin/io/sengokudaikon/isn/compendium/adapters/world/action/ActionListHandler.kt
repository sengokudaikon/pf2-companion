package io.sengokudaikon.isn.compendium.adapters.world.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.character.ListActionPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ActionListHandler :
    ListHandler<List<ActionModel>, ActionQuery.All, ListActionPort>() {
    override val useCase: ListActionPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): ActionQuery.All {
        return ActionQuery.All(page, size)
    }
}
