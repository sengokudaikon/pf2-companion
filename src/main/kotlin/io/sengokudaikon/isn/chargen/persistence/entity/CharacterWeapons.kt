package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.persistence.items.entity.Weapons
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterWeapons : KotlinxUUIDTable("char_weapons") {
    val characterID = reference("characterID", Characters)
    val weaponID = reference("weaponID", Weapons)
}
