package io.sengokudaikon.isn.compendium.operations.character.ancestry.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface AncestryFeatsQuery : Query {
    @Resource("/api/ancestry/{id}/feats")
    data class All(val id: String, override val page: Int, override val size: Int) :
        Query.All<List<AncestryFeatureModel>>, AncestryFeatsQuery {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/ancestry/{id}/feats/{name}")
    data class ByName(val id: String, override val name: String) :
        Query.ByName<AncestryFeatureModel>, AncestryFeatsQuery
}
