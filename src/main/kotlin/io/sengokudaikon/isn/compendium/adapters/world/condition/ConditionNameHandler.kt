package io.sengokudaikon.isn.compendium.adapters.world.condition

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameConditionPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ConditionNameHandler :
    ByNameHandler<ConditionModel, ConditionQuery.ByName, ByNameConditionPort>() {
    override val useCase: ByNameConditionPort by inject()
    override fun createQuery(name: String, id: String?): ConditionQuery.ByName {
        return ConditionQuery.ByName(name)
    }
}
