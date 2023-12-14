package io.sengokudaikon.isn.compendium.operations.search.query

import io.ktor.resources.*
import io.sengokudaikon.isn.infrastructure.operations.Query
import kotlinx.serialization.Serializable

@Resource("/api/search")
data class SearchQuery(
    val query: String? = null,
    val type: String? = null,
    val rarity: String? = null,
    val traits: List<String>? = null,
    val numbers: List<NumericFilter>? = null,
) : Query {
    @Serializable
    data class NumericFilter(val type: String, val value: Int)
}
