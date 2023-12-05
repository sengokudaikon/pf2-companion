package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import kotlinx.serialization.Serializable

interface SearchPort : ReadPort<SearchPort.Query, List<SearchResult>> {
    @Serializable
    data class Query(
        val query: String? = null,
    )
}
