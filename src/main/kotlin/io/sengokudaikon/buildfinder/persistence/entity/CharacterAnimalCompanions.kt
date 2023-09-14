package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.character.companion.entity.AnimalCompanions
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterAnimalCompanions : KotlinxUUIDTable("char_animal_companions") {
    val characterID = reference("characterID", Characters).nullable()
    val animalCompanionID = reference("animalCompanionID", AnimalCompanions)
}
