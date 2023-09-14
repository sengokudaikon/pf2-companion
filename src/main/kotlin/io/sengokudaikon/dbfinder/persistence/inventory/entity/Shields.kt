package io.sengokudaikon.dbfinder.persistence.inventory.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Items
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Shields : KotlinxUUIDTable("shields") {
    val itemId = reference("item_id", Items)
    val acBonus = integer("ac_bonus")
    val hp = integer("hp")
    val hardness = integer("hardness")
    val brokenThreshold = integer("broken_threshold")
    val hidden = bool("hidden").default(false)
}
