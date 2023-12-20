package io.sengokudaikon.isn.compendium.usecases.world.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.world.ListActionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList

class ActionList(override val repository: ActionRepositoryPort) :
    GetList<ActionQuery, ActionModel, List<ActionModel>, ActionRepositoryPort>(), ListActionPort {
    override fun getCacheKey(query: ActionQuery): String {
        query as ActionQuery.All
        return "model_action:all:${query.page}:${query.size}"
    }
}
