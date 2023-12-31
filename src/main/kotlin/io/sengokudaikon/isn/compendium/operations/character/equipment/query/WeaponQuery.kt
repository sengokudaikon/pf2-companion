package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface WeaponQuery : EquipmentQuery {
    @Resource("/api/equipment/weapons")
    data class All(override val page: Int, override val size: Int) : WeaponQuery, Query.All<List<WeaponModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/equipment/weapon/{id}")
    data class ById(override val id: String) : WeaponQuery, Query.ById<WeaponModel>

    @Resource("/api/equipment/weapon")
    data class ByName(override val name: String) : WeaponQuery, Query.ByName<WeaponModel>
}
