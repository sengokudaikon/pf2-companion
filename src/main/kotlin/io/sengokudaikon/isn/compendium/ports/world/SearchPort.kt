package io.sengokudaikon.isn.compendium.ports.world

import io.sengokudaikon.isn.compendium.domain.world.model.SearchResult
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

interface SearchPort : ReadPort<SearchQuery, List<SearchResult>>
