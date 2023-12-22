package io.sengokudaikon.isn.compendium.usecases.character.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameActionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameActionPort::class])
class ActionByName(override val repository: ActionRepositoryPort) :
    GetByName<ActionQuery, ActionModel>(), ByNameActionPort {
    override fun getCacheKey(query: ActionQuery): String {
        query as ActionQuery.ByName
        return "model_action:name:${query.name}"
    }
}
