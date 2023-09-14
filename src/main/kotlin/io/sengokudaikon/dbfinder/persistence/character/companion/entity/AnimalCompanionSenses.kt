package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.VisionSenses
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AnimalCompanionSenses : KotlinxUUIDTable("animal_companion_senses") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val sense = reference("sense", VisionSenses)
}
