package io.sengokudaikon.isn.compendium.operations.search.query

import io.ktor.resources.*
import io.sengokudaikon.isn.infrastructure.operations.Query

@Resource("/api/search/{query}")
data class SearchQuery(
    val query: String? = null,
) : Query
