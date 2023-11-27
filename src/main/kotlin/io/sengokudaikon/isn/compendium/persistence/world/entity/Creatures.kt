package io.sengokudaikon.isn.compendium.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Creatures : KotlinxUUIDTable("creatures") {
    val name = varchar("name", 50)
    val description = text("description")
    val level = integer("level")
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", length = 10)
    val size = enumerationByName<io.sengokudaikon.isn.compendium.enums.Size>("size", length = 10)
    val alignment = enumerationByName<io.sengokudaikon.isn.compendium.enums.Alignment>("alignment", 50)
}
