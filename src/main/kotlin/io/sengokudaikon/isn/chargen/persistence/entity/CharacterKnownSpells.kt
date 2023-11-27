package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.persistence.items.entity.Spells
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterKnownSpells : KotlinxUUIDTable("char_known_spells") {
    val characterID = reference("characterID", Characters)
    val spellID = reference("spellID", Spells)
}
