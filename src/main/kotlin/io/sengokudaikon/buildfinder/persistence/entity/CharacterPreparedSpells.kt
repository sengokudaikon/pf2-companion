package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Spells
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterPreparedSpells : KotlinxUUIDTable("char_prepared_spells") {
    val characterID = reference("characterID", Characters)
    val spellID = reference("spellID", Spells)
    val preparedLevel = integer("preparedLevel")
}
