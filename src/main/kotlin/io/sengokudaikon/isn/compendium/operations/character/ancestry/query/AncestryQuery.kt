package io.sengokudaikon.isn.compendium.operations.character.ancestry.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface AncestryQuery : Query {
    @Resource("/api/ancestry/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : Query.All<List<AncestryModel>>, AncestryQuery

    @Resource("/api/ancestry/{id}")
    data class ById(override val id: String) : Query.ById<AncestryModel>, AncestryQuery

    @Resource("/api/ancestry/name/{name}")
    data class ByName(override val name: String) : Query.ByName<AncestryModel>, AncestryQuery
}
