package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.enums.Proficiency
import io.sengokudaikon.dbfinder.persistence.enums.Skills
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AnimalCompanionSkills : KotlinxUUIDTable("animal_companion_skills") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val skill = enumerationByName<Skills>("skill", 50)
    val proficiency = enumerationByName<Proficiency>("proficiency", 50).default(Proficiency.UNTRAINED)
}
