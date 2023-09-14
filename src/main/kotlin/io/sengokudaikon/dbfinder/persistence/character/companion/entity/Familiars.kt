package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Familiars : KotlinxUUIDTable("char_familiars") {
    val name = varchar("name", 50)
    val type = varchar("type", 50)
    val rarity = enumerationByName<Rarity>("rarity", 50)
    val hp = integer("hp")
    val contentSrc = varchar("content_src", 100)
    val description = varchar("description", 500)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
