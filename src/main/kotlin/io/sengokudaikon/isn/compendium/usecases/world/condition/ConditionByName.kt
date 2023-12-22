package io.sengokudaikon.isn.compendium.usecases.world.condition

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.domain.condition.repository.ConditionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameConditionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameConditionPort::class])
class ConditionByName(override val repository: ConditionRepositoryPort) :
    GetByName<ConditionQuery, ConditionModel>(), ByNameConditionPort {
    override fun getCacheKey(query: ConditionQuery): String {
        query as ConditionQuery.ByName
        return "model_condition:name:${query.name}"
    }
}