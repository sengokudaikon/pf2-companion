package io.sengokudaikon.isn.compendium.adapters.world.condition

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.compendium.ports.world.ListConditionPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ConditionListHandler :
    ListHandler<List<ConditionModel>, ConditionQuery.All, ListConditionPort>() {
    override val useCase: ListConditionPort by inject()
    override fun createQuery(page: Int, size: Int, filters: String?, id: String?): ConditionQuery.All {
        return ConditionQuery.All(page, size, filters)
    }
}
