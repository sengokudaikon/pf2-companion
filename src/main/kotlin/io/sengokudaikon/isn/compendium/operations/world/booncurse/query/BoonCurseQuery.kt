package io.sengokudaikon.isn.compendium.operations.world.booncurse.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface BoonCurseQuery : Query {
    @Resource("/api/boonCurses/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : Query.All<List<BoonCurseModel>>, BoonCurseQuery

    @Resource("/api/boonCurses/{id}")
    data class ById(override val id: String) : Query.ById<BoonCurseModel>, BoonCurseQuery

    @Resource("/api/noonCurses/name/{name}")
    data class ByName(override val name: String) : Query.ByName<BoonCurseModel>, BoonCurseQuery
}
