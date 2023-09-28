package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.Ancestries
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Heritages : KotlinxUUIDTable("char_heritages") {
    val name = varchar("name", length = 50)
    val description = text("description")
    val ancestryID = reference("ancestry_id", Ancestries)
    val rarity = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Rarity>("rarity", length = 10)
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", length = 100)
    val code = varchar("code", length = 10)
    val homebrewID = integer("homebrew_id").nullable().default(null)
    val individualName = varchar("individual_name", length = 50)
}
