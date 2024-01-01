package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface KitQuery: Query {
    @Resource("/api/equipment/kits")
    data class All(override val page: Int, override val size: Int) : KitQuery, Query.All<List<KitModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/equipment/kit/{id}")
    data class ById(override val id: String) : KitQuery, Query.ById<KitModel>

    @Resource("/api/equipment/kit")
    data class ByName(override val name: String) : KitQuery, Query.ByName<KitModel>
}
