package io.sengokudaikon.isn.compendium.operations.character.ancestry.query

import io.ktor.resources.*
import io.sengokudaikon.isn.infrastructure.operations.Query

interface AncestryQuery : Query {
    @Resource("/api/ancestry/list/{page}/{limit}")
    data class FindAll(val page: Int, val limit: Int) : AncestryQuery

    @Resource("/api/ancestry/{id}")
    data class FindById(val id: String) : AncestryQuery

    @Resource("/api/ancestry/name/{name}")
    data class FindByName(val name: String) : AncestryQuery
}
