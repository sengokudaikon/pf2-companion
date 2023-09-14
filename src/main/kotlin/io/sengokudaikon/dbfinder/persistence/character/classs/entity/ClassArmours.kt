package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.enums.ProficiencyArmour
import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassArmours : KotlinxUUIDTable("char_class_armour") {
    val classID = reference("classID", Classes)
    val proficiencyArmour = enumerationByName<ProficiencyArmour>(
        "proficiencyArmour",
        length = 20,
    )
}
