package io.sengokudaikon.isn.compendium.operations.character.equipment.query

import io.ktor.resources.*
import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface ArmorQuery : EquipmentQuery {
    @Resource("/api/equipment/armors")
    data class All(override val page: Int, override val size: Int) : ArmorQuery, Query.All<List<ArmorModel>> {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/equipment/armor/{id}")
    data class ById(override val id: String) : ArmorQuery, Query.ById<ArmorModel>

    @Resource("/api/equipment/armor")
    data class ByName(override val name: String) : ArmorQuery, Query.ByName<ArmorModel>
}
