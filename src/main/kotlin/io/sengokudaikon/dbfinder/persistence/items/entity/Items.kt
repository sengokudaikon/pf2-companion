package io.sengokudaikon.dbfinder.persistence.items.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.ItemHands
import io.sengokudaikon.dbfinder.infrastructure.enums.ItemType
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.Size
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Items : KotlinxUUIDTable("items") {
    val name = varchar("name", 50)
    val description = text("description")
    val price = integer("cost")
    val bulk = integer("weight")
    val level = integer("level")
    val rarity = enumerationByName<Rarity>("rarity", 10)
    val itemType = enumerationByName<ItemType>("item_type", 20)
    val craftRequirements = text("craft_requirements")
    val usage = text("usage")
    val hands = enumerationByName<ItemHands>("hands", 10)
    val size = enumerationByName<Size>("size", 10)
}

object ItemTraits : KotlinxUUIDTable("item_traits") {
    val itemID = reference("item_id", Items)
    val trait = varchar("trait", 50)
}
