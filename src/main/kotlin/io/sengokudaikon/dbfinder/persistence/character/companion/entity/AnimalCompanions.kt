package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.Size
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AnimalCompanions : KotlinxUUIDTable("char_animal_companions") {
    val name = varchar("name", 50)
    val rarity = enumerationByName<Rarity>("rarity", length = 10)
    val description = text("description")
    val size = enumerationByName<Size>("size", length = 10)
    val hp = integer("hp")
    val ac = integer("ac")
    val speed = integer("speed")
}
