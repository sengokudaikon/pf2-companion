package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency
import io.sengokudaikon.dbfinder.infrastructure.enums.WeaponClass
import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassWeapons : KotlinxUUIDTable("char_class_weapons") {
    val classID = reference("classID", Classes)
    val weaponIDs = varchar("weaponIDs", 100)
    val weaponClass = enumerationByName<WeaponClass>(
        "weaponClass",
        length = 20,
    )
    val proficiency = enumerationByName<Proficiency>(
        "proficiency",
        length = 20,
    )
}
