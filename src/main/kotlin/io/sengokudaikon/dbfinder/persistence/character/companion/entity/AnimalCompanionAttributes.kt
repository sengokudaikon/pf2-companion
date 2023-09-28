package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AnimalCompanionAttributes : KotlinxUUIDTable("animal_companion_attributes") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val attribute = enumerationByName<Ability>("attribute", 10)
    val value = integer("value")
}
