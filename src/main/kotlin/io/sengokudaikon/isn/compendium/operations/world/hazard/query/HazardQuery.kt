package io.sengokudaikon.isn.compendium.operations.world.hazard.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface HazardQuery : Query {
    @Resource("/api/hazards/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : Query.All<List<HazardModel>>, HazardQuery

    @Resource("/api/hazards/{id}")
    data class ById(override val id: String) : Query.ById<HazardModel>, HazardQuery

    @Resource("/api/hazards/name/{name}")
    data class ByName(override val name: String) : Query.ByName<HazardModel>, HazardQuery
}
