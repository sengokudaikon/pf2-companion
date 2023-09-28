package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import kotlinx.uuid.exposed.KotlinxUUIDTable

object BackgroundSkills : KotlinxUUIDTable("background_skills") {
    val background = reference("background_id", Backgrounds)
    val skill = enumerationByName<Skills>("skill", 50)
    val proficiency =
        enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency>("skillProficiency", 50).default(
            io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency.UNTRAINED,
        )
}
