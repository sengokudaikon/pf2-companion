package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.enums.SpellTraditions
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterSpellcastings : KotlinxUUIDTable("char_spellcasting") {
    val characterID = reference("characterID", Characters)
    val spellcastingTradition = enumerationByName<SpellTraditions>("spellcastingTradition", 10)
}
