package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.inventory.entity.Weapons
import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassSpecificWeapons : KotlinxUUIDTable("char_class_specific_weapons") {
    val classID = reference("class_id", Classes)
    val weaponID = reference("weapon_id", Weapons)
}
