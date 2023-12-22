package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ShieldQuery : EquipmentQuery {
    @Resource("/api/equipment/shield/list/{page}/{size}")
    data class All(override val page: Int, override val size: Int, override val filters: String?) : ShieldQuery, Query.All<List<ShieldModel>>

    @Resource("/api/equipment/shield/{id}")
    data class ById(override val id: String) : ShieldQuery, Query.ById<ShieldModel>

    @Resource("/api/equipment/shield/name/{name}")
    data class ByName(override val name: String) : ShieldQuery, Query.ByName<ShieldModel>
}
