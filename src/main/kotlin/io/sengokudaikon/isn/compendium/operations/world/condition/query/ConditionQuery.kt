package io.sengokudaikon.isn.compendium.operations.world.condition.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ConditionQuery : Query {
    @Resource("/api/conditions/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int, override val filters: String?) : Query.All<List<ConditionModel>>, ConditionQuery

    @Resource("/api/conditions/{id}")
    data class ById(override val id: String) : Query.ById<ConditionModel>, ConditionQuery

    @Resource("/api/conditions/name/{name}")
    data class ByName(override val name: String) : Query.ByName<ConditionModel>, ConditionQuery
}
