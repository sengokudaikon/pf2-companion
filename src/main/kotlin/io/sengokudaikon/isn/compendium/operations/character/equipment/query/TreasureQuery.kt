package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface TreasureQuery: Query {
    @Resource("/api/equipment/treasures")
    data class All(override val page: Int, override val size: Int) : TreasureQuery, Query.All<List<TreasureModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/equipment/treasure/{id}")
    data class ById(override val id: String) : TreasureQuery, Query.ById<TreasureModel>

    @Resource("/api/equipment/treasure")
    data class ByName(override val name: String) : TreasureQuery, Query.ByName<TreasureModel>
}
