package io.sengokudaikon.dbfinder.persistence.world.entity

import io.sengokudaikon.dbfinder.persistence.enums.ItemHands
import io.sengokudaikon.dbfinder.persistence.enums.ItemType
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.Size
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Items : KotlinxUUIDTable("items") {
    val name = varchar("name", 50)
    val description = text("description")
    val price = integer("cost")
    val bulk = integer("weight")
    val level = integer("level")
    val rarity = enumerationByName<Rarity>("rarity", 10)
    val itemType = enumerationByName<ItemType>("itemType", 20)
    val craftRequirements = text("craftRequirements")
    val usage = text("usage")
    val hands = enumerationByName<ItemHands>("hands", 10)
    val size = enumerationByName<Size>("size", 10)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
