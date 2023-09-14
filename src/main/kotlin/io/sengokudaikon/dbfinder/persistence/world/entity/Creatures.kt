package io.sengokudaikon.dbfinder.persistence.world.entity

import io.sengokudaikon.dbfinder.persistence.enums.Alignment
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.Size
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Creatures : KotlinxUUIDTable("creatures") {
    val name = varchar("name", 50)
    val description = text("description")
    val level = integer("level")
    val rarity = enumerationByName<Rarity>("rarity", length = 10)
    val size = enumerationByName<Size>("size", length = 10)
    val alignment = enumerationByName<Alignment>("alignment", 50)
}
