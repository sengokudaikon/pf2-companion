package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ArmorQuery : EquipmentQuery {
    @Resource("/api/equipment/armor/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int) : ArmorQuery, Query.All<List<ArmorModel>>

    @Resource("/api/equipment/armor/{id}")
    data class ById(override val id: String) : ArmorQuery, Query.ById<ArmorModel>

    @Resource("/api/equipment/armor/name/{name}")
    data class ByName(override val name: String) : ArmorQuery, Query.ByName<ArmorModel>
}
