package io.sengokudaikon.isn.compendium.operations.world.bestiary.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface BestiaryQuery : Query {
    @Resource("/api/bestiary/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : Query.All<List<BestiaryModel>>, BestiaryQuery

    @Resource("/api/bestiary/{id}")
    data class ById(override val id: String) : Query.ById<BestiaryModel>, BestiaryQuery

    @Resource("/api/bestiary/name/{name}")
    data class ByName(override val name: String) : Query.ByName<BestiaryModel>, BestiaryQuery
}
