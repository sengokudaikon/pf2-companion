package io.sengokudaikon.isn.compendium.adapters.world.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.operations.world.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameActionPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ActionNameHandler : ByNameHandler<ActionModel, ActionQuery.ByName, ByNameActionPort>() {
    override val useCase: ByNameActionPort by inject()
    override fun createQuery(name: String, id: String?): ActionQuery.ByName {
        return ActionQuery.ByName(name)
    }
}