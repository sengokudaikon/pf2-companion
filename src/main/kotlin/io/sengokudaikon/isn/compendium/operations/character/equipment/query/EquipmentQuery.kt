package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface EquipmentQuery : Query {
    @Resource("/api/equipment/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int, override val filters: String?) : EquipmentQuery, Query.All<List<EquipmentModel>>

    @Resource("/api/equipment/{id}")
    data class ById(override val id: String) : EquipmentQuery, Query.ById<EquipmentModel>

    @Resource("/api/equipment/name/{name}")
    data class ByName(override val name: String) : EquipmentQuery, Query.ByName<EquipmentModel>
}
