package io.sengokudaikon.isn.compendium.operations.character.ancestry.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface AncestryFeatsQuery : Query {
    @Resource("/api/ancestry/{id}/feats/{page}/{size}")
    data class All(val id: String, override val page: Int, override val size: Int, override val filters: String?) :
        Query.All<List<AncestryFeatureModel>>, AncestryFeatsQuery

    @Resource("/api/ancestry/{id}/feats/{name}")
    data class ByName(val id: String, override val name: String) :
        Query.ByName<AncestryFeatureModel>, AncestryFeatsQuery
}
