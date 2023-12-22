package io.sengokudaikon.isn.compendium.operations.world.condition.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ConditionQuery : Query {
    @Resource("/api/conditions")
    data class All(override val page: Int, override val size: Int,) :
        Query.All<List<ConditionModel>>, ConditionQuery {
        override var filters: String? = null
        override var sort: String? = null
        }

    @Resource("/api/condition/{id}")
    data class ById(override val id: String) : Query.ById<ConditionModel>, ConditionQuery

    @Resource("/api/condition")
    data class ByName(override val name: String) : Query.ByName<ConditionModel>, ConditionQuery
}
