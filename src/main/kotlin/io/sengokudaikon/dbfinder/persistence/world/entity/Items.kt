package io.sengokudaikon.dbfinder.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Items : KotlinxUUIDTable("items") {
    val name = varchar("name", 50)
    val description = text("description")
    val price = integer("cost")
    val bulk = integer("weight")
    val level = integer("level")
    val rarity = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Rarity>("rarity", 10)
    val itemType = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.ItemType>("itemType", 20)
    val craftRequirements = text("craftRequirements")
    val usage = text("usage")
    val hands = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.ItemHands>("hands", 10)
    val size = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Size>("size", 10)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
