package io.sengokudaikon.isn.compendium.operations.world.booncurse.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface BoonCurseQuery : Query {
    @Resource("/api/boonCurses")
    data class All(override val page: Int, override val size: Int, ) :
        Query.All<List<BoonCurseModel>>, BoonCurseQuery {
        override var filters: String? = null
        override var sort: String? = null
        }

    @Resource("/api/boonCurse/{id}")
    data class ById(override val id: String) : Query.ById<BoonCurseModel>, BoonCurseQuery

    @Resource("/api/noonCurse")
    data class ByName(override val name: String) : Query.ByName<BoonCurseModel>, BoonCurseQuery
}
