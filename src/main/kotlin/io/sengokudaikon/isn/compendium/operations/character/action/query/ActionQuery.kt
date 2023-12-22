package io.sengokudaikon.isn.compendium.operations.character.action.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ActionQuery : Query {
    @Resource("/api/actions")
    data class All(override val page: Int, override val size: Int) :
        Query.All<List<ActionModel>>, ActionQuery {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/action/{id}")
    data class ById(override val id: String) : Query.ById<ActionModel>, ActionQuery

    @Resource("/api/action")
    data class ByName(override val name: String) : Query.ByName<ActionModel>, ActionQuery
}
