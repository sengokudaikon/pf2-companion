package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Spells
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterKnownSpells : KotlinxUUIDTable("char_known_spells") {
    val characterID = reference("characterID", Characters)
    val spellID = reference("spellID", Spells)
}
