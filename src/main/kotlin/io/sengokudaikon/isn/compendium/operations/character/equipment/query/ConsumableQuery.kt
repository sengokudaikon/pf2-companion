package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ConsumableQuery: Query {
    @Resource("/api/equipment/consumables")
    data class All(override val page: Int, override val size: Int) : ConsumableQuery, Query.All<List<ConsumableModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/equipment/consumable/{id}")
    data class ById(override val id: String) : ConsumableQuery, Query.ById<ConsumableModel>

    @Resource("/api/equipment/consumable")
    data class ByName(override val name: String) : ConsumableQuery, Query.ByName<ConsumableModel>
}
