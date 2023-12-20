package io.sengokudaikon.isn.compendium.usecases.world.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdActionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById

class ActionById(override val repository: ActionRepositoryPort) :
    GetById<ActionQuery, ActionModel, ActionRepositoryPort>(), ByIdActionPort {
    override fun getCacheKey(query: ActionQuery): String {
        query as ActionQuery.ById
        return "model_action:id:${query.id}"
    }
}
