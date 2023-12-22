package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface EquipmentQuery : Query {
    @Resource("/api/equipments")
    data class All(override val page: Int, override val size: Int) : EquipmentQuery, Query.All<List<EquipmentModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/equipment/{id}")
    data class ById(override val id: String) : EquipmentQuery, Query.ById<EquipmentModel>

    @Resource("/api/equipment")
    data class ByName(override val name: String) : EquipmentQuery, Query.ByName<EquipmentModel>
}
