package io.sengokudaikon.isn.compendium.persistence.items.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Shields : KotlinxUUIDTable("items_shields") {
    val itemId = reference("item_id", Items)
    val acBonus = integer("ac_bonus")
    val hp = integer("hp")
    val hardness = integer("hardness")
    val brokenThreshold = integer("broken_threshold")
    val hidden = bool("hidden").default(false)
}

object ShieldTraits : KotlinxUUIDTable("item_shield_traits") {
    val shieldId = reference("shield_id", Shields)
    val trait = varchar("trait", 50)
}
