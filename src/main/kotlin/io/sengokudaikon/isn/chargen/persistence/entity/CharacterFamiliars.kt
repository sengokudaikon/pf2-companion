package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.persistence.character.companion.entity.Familiars
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterFamiliars : KotlinxUUIDTable("char_familiars") {
    val characterID = reference("characterID", Characters).nullable()
    val familiarID = reference("familiarID", Familiars)
}
