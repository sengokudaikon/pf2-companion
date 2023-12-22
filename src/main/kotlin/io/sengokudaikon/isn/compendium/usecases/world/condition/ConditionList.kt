package io.sengokudaikon.isn.compendium.usecases.world.condition

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.domain.condition.repository.ConditionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.compendium.ports.world.ListConditionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListConditionPort::class])
class ConditionList(override val repository: ConditionRepositoryPort) :
    GetList<ConditionQuery, ConditionModel>(), ListConditionPort {
    override fun getCacheKey(query: ConditionQuery): String {
        query as ConditionQuery.All
        return "model_condition:all:${query.page}:${query.size}"
    }
}