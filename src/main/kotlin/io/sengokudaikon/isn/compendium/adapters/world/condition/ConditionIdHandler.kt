package io.sengokudaikon.isn.compendium.adapters.world.condition

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdConditionPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ConditionIdHandler :
    ByIdHandler<ConditionModel, ConditionQuery.ById, ByIdConditionPort>() {
    override val useCase: ByIdConditionPort by inject()
    override fun createQuery(id: String): ConditionQuery.ById {
        return ConditionQuery.ById(id)
    }
}
