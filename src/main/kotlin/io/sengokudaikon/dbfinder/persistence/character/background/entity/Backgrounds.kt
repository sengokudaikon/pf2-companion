package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Backgrounds : KotlinxUUIDTable("char_backgrounds") {
    val name = varchar("name", length = 50)
    val rarity = enumerationByName<Rarity>("rarity", length = 10)
    val description = text("description")
    val code = varchar("code", length = 10)
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", length = 100)
    val homebrewID = integer("homebrew_id").nullable().default(null)
    val version = varchar("version", length = 10)
}
