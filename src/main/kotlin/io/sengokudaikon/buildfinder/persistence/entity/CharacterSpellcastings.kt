package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.enums.SpellTraditions
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterSpellcastings : KotlinxUUIDTable("char_spellcasting") {
    val characterID = reference("characterID", Characters)
    val spellcastingTradition = enumerationByName<SpellTraditions>("spellcastingTradition", 10)
}
