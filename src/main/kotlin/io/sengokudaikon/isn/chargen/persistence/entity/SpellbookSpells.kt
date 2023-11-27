package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.enums.SpellTraditions
import io.sengokudaikon.isn.compendium.persistence.items.entity.Spells
import kotlinx.uuid.exposed.KotlinxUUIDTable

object SpellbookSpells : KotlinxUUIDTable("char_spellbook_spells") {
    val characterId = reference("character_id", Characters)
    val spellId = reference("spell_id", Spells)
    val spellLevel = integer("spell_level")
    val isAutoHeightened = bool("is_auto_heightened").default(true)
    val heightenLevel = integer("heighten_level")
    val traditionID = enumerationByName<SpellTraditions>("tradition", length = 10)
    val spellType = varchar("spell_type", 50)
    val structureHashed = text("structure_hashed")
}
