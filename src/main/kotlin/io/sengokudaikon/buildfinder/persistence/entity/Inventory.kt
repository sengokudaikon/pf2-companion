package io.sengokudaikon.buildfinder.persistence.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Inventory : KotlinxUUIDTable("char_inventory") {
    val characterID = reference("characterID", Characters)
    val platinum = integer("platinum")
    val gold = integer("gold")
    val silver = integer("silver")
    val copper = integer("copper")
}
