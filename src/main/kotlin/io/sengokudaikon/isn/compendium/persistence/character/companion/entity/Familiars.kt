package io.sengokudaikon.isn.compendium.persistence.character.companion.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Familiars : KotlinxUUIDTable("char_familiars") {
    val name = varchar("name", 50)
    val type = varchar("type", 50)
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", 50)
    val hp = integer("hp")
    val contentSrc = varchar("content_src", 100)
    val description = varchar("description", 500)
}
