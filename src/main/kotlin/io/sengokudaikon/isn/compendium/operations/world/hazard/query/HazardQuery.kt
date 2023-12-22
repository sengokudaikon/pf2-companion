package io.sengokudaikon.isn.compendium.operations.world.hazard.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface HazardQuery : Query {
    @Resource("/api/hazards")
    data class All(override val page: Int, override val size: Int) :
        Query.All<List<HazardModel>>, HazardQuery {
        override var filters: String? = null
        override var sort: String? = null
        }

    @Resource("/api/hazard/{id}")
    data class ById(override val id: String) : Query.ById<HazardModel>, HazardQuery

    @Resource("/api/hazard")
    data class ByName(override val name: String) : Query.ByName<HazardModel>, HazardQuery
}
