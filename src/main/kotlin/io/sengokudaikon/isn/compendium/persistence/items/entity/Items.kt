package io.sengokudaikon.isn.compendium.persistence.items.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Items : KotlinxUUIDTable("items") {
    val name = varchar("name", 50)
    val description = text("description")
    val price = integer("cost")
    val bulk = integer("weight")
    val level = integer("level")
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", 10)
    val itemType = enumerationByName<io.sengokudaikon.isn.compendium.enums.ItemType>("item_type", 20)
    val craftRequirements = text("craft_requirements")
    val usage = text("usage")
    val hands = enumerationByName<io.sengokudaikon.isn.compendium.enums.ItemHands>("hands", 10)
    val size = enumerationByName<io.sengokudaikon.isn.compendium.enums.Size>("size", 10)
}

object ItemTraits : KotlinxUUIDTable("item_traits") {
    val itemID = reference("item_id", Items)
    val trait = varchar("trait", 50)
}
