package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.persistence.items.entity.Items
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterGears : KotlinxUUIDTable("char_gears") {
    val characterID = reference("character_id", Characters)
    val gearID = reference("item_id", Items)
    val isInvested = bool("is_invested").default(false)
    val quantity = integer("quantity").default(1)
}
