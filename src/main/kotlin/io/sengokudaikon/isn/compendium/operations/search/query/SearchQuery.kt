package io.sengokudaikon.isn.compendium.operations.search.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.infrastructure.operations.Query

@Resource("/api/search")
data class SearchQuery(
    val query: String? = null,
    val filters: List<Filter> = emptyList(),
) : Query

