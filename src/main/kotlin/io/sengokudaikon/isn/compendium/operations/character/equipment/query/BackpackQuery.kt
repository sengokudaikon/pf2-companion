package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface BackpackQuery: Query {
    @Resource("/api/equipment/backpacks")
    data class All(override val page: Int, override val size: Int) : BackpackQuery, Query.All<List<BackpackModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/equipment/backpack/{id}")
    data class ById(override val id: String) : BackpackQuery, Query.ById<BackpackModel>

    @Resource("/api/equipment/backpack")
    data class ByName(override val name: String) : BackpackQuery, Query.ByName<BackpackModel>
}
