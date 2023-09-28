package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassSkills : KotlinxUUIDTable("char_class_skills") {
    val classID = reference("classID", Classes)
    val skillID = enumerationByName<Skills>("skillID", 10)
    val proficiency =
        enumerationByName<Proficiency>("skillProficiency", 20)
}
