package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Items
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterGears : KotlinxUUIDTable("char_gears") {
    val characterID = reference("character_id", Characters)
    val gearID = reference("gear_id", Items)
    val isInvested = bool("is_Invested").default(false)
    val quantity = integer("quantity").default(1)
}
