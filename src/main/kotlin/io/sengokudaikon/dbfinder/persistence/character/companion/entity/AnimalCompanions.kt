package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object AnimalCompanions : KotlinxUUIDTable("char_animal_companions") {
    val name = varchar("name", 50)
    val rarity = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Rarity>("rarity", length = 10)
    val description = text("description")
    val size = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Size>("size", length = 10)
    val hp = integer("hp")
    val ac = integer("ac")
    val speed = integer("speed")
}
