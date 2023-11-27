package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.enums.Skills
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterSkills : KotlinxUUIDTable("char_skills") {
    val characterID = reference("characterID", Characters)
    val skillID = enumerationByName<Skills>("skillID", 50)
    val value = integer("value")
    val currentProficiency =
        enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("proficiency", 10)
}
