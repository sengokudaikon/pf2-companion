package io.sengokudaikon.isn.compendium.operations.world.action.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ActionQuery : Query {
    @Resource("/api/actions/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : Query.All<List<ActionModel>>, ActionQuery

    @Resource("/api/actions/{id}")
    data class ById(override val id: String) : Query.ById<ActionModel>, ActionQuery

    @Resource("/api/actions/name/{name}")
    data class ByName(override val name: String) : Query.ByName<ActionModel>, ActionQuery
}
