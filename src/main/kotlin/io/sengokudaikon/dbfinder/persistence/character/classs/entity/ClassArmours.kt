package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassArmours : KotlinxUUIDTable("char_class_armour") {
    val classID = reference("classID", Classes)
    val armour = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.ArmourProficiencyIn>(
        "armourClass",
        length = 20,
    )
    val proficiency =
        enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency>("armourProficiency", 10)
}
