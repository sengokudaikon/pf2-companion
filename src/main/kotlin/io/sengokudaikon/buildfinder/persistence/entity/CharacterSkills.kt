package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.enums.Proficiency
import io.sengokudaikon.dbfinder.persistence.enums.Skills
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterSkills : KotlinxUUIDTable("char_skills") {
    val characterID = reference("characterID", Characters)
    val skillID = enumerationByName<Skills>("skillID", 50)
    val value = integer("value")
    val currentProficiency = enumerationByName<Proficiency>("proficiency", 10)
}
