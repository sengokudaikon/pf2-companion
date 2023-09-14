package io.sengokudaikon.dbfinder.persistence.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.inventory.entity.Weapons
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AnimalCompanionWeapons : KotlinxUUIDTable("animal_companion_weapons") {
    val animalCompanionID = reference("animal_companion_id", AnimalCompanions)
    val weapon = reference("weapon", Weapons)
}
