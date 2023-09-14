package io.sengokudaikon.buildfinder.persistence.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterSpellSlots : KotlinxUUIDTable("char_spell_slots") {
    val characterID = reference("characterID", Characters)
    val spellLevel = integer("spellLevel")
    val maxSlots = integer("maxSlots")
    val usedSlots = integer("usedSlots")
}
