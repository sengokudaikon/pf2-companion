package io.sengokudaikon.dbfinder.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Creatures : KotlinxUUIDTable("creatures") {
    val name = varchar("name", 50)
    val description = text("description")
    val level = integer("level")
    val rarity = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Rarity>("rarity", length = 10)
    val size = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Size>("size", length = 10)
    val alignment = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Alignment>("alignment", 50)
}
