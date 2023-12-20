package io.sengokudaikon.isn.compendium.usecases.world.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameActionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class ActionByName(override val repository: ActionRepositoryPort) :
    GetByName<ActionQuery, ActionModel, ActionRepositoryPort>(), ByNameActionPort {
    override fun getCacheKey(query: ActionQuery): String {
        query as ActionQuery.ByName
        return "model_action:name:${query.name}"
    }
}
