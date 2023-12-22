package io.sengokudaikon.isn.compendium.operations.world.bestiary.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface BestiaryQuery : Query {
    @Resource("/api/bestiaries")
    data class All(override val page: Int, override val size: Int,) :
        Query.All<List<BestiaryModel>>, BestiaryQuery {
        override var filters: String? = null
        override var sort: String? = null
        }

    @Resource("/api/bestiary/{id}")
    data class ById(override val id: String) : Query.ById<BestiaryModel>, BestiaryQuery

    @Resource("/api/bestiary")
    data class ByName(override val name: String) : Query.ByName<BestiaryModel>, BestiaryQuery
}
