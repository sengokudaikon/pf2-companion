package io.sengokudaikon.isn.compendium.operations.character.heritage.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface HeritageQuery : Query {
    @Resource("/api/heritages")
    data class All(override val page: Int, override val size: Int) : HeritageQuery, Query.All<List<HeritageModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/heritage/{id}")
    data class ById(override val id: String) : HeritageQuery, Query.ById<HeritageModel>

    @Resource("/api/heritage")
    data class ByName(override val name: String) : HeritageQuery, Query.ByName<HeritageModel>
}
