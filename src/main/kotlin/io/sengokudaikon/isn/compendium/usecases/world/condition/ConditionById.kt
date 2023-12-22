package io.sengokudaikon.isn.compendium.usecases.world.condition

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.compendium.domain.condition.repository.ConditionRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.condition.query.ConditionQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdConditionPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdConditionPort::class])
class ConditionById(override val repository: ConditionRepositoryPort) :
    GetById<ConditionQuery, ConditionModel>(), ByIdConditionPort {
    override fun getCacheKey(query: ConditionQuery): String {
        query as ConditionQuery.ById
        return "model_condition:id:${query.id}"
    }
}