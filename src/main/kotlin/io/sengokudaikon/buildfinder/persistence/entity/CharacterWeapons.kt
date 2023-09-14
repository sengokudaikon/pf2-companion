package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.inventory.entity.Weapons
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterWeapons : KotlinxUUIDTable("char_weapons") {
    val characterID = reference("characterID", Characters)
    val weaponID = reference("weaponID", Weapons)
}
