package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Languages
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryLanguages : KotlinxUUIDTable("char_ancestry_languages") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val languageID = reference("language_id", Languages)
    val isBonus = bool("is_bonus").default(false)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
