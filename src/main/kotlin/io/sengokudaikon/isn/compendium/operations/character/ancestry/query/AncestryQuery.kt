package io.sengokudaikon.isn.compendium.operations.character.ancestry.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

interface AncestryQuery : Query {
    @Resource("/api/ancestry/list/{page}/{size}")
    @Schema(name = "AncestryList", description = "List of ancestries")
    @Tag(name = "Ancestry")
    data class All(override val page: Int, override val size: Int) : Query.All<List<AncestryModel>>, AncestryQuery

    @Resource("/api/ancestry/{id}")
    @Schema(name = "AncestryById", description = "Ancestry by id")
    @Tag(name = "Ancestry")
    data class ById(override val id: String) : Query.ById<AncestryModel>, AncestryQuery

    @Resource("/api/ancestry/name/{name}")
    @Schema(name = "AncestryByName", description = "Ancestry by name")
    @Tag(name = "Ancestry")
    data class ByName(override val name: String) : Query.ByName<AncestryModel>, AncestryQuery
}
