package io.sengokudaikon.isn.compendium.operations.world.deity.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface DeityQuery : Query {
    @Resource("/api/deities")
    data class All(override val page: Int, override val size: Int) :
        Query.All<List<DeityModel>>, DeityQuery {
        override var filters: String? = null
        override var sort: String? = null
        }

    @Resource("/api/deity/{id}")
    data class ById(override val id: String) : Query.ById<DeityModel>, DeityQuery

    @Resource("/api/deity")
    data class ByName(override val name: String) : Query.ByName<DeityModel>, DeityQuery
}
