package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.persistence.world.entity.Languages
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterLanguages : KotlinxUUIDTable("char_languages") {
    val characterID = reference("characterID", Characters)
    val languageID = reference("languageID", Languages)
}
