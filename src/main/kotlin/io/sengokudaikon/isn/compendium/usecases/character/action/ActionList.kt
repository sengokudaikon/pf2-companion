package io.sengokudaikon.isn.compendium.usecases.character.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.character.ListActionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListActionPort::class])
class ActionList(override val repository: ActionRepositoryPort) : GetList<ActionQuery, ActionModel>(), ListActionPort {
    override fun getCacheKey(query: ActionQuery): String {
        query as ActionQuery.All
        return "model_action:all:${query.page}:${query.size}"
    }
}
