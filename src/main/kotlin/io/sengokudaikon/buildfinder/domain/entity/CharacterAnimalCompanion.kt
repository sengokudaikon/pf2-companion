package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterAnimalCompanions
import io.sengokudaikon.dbfinder.domain.character.companion.entity.AnimalCompanion
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterAnimalCompanion(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterAnimalCompanion>(CharacterAnimalCompanions)

    val character by Character optionalReferencedOn CharacterAnimalCompanions.characterID
    val animalCompanion by AnimalCompanion referencedOn CharacterAnimalCompanions.animalCompanionID
}
