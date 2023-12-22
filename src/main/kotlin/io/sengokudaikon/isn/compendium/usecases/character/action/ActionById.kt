package io.sengokudaikon.isn.compendium.usecases.character.action

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.action.repository.ActionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.action.query.ActionQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdActionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdActionPort::class])
class ActionById(override val repository: ActionRepositoryPort) :
    GetById<ActionQuery, ActionModel>(), ByIdActionPort {
    override fun getCacheKey(query: ActionQuery): String {
        query as ActionQuery.ById
        return "model_action:id:${query.id}"
    }
}
