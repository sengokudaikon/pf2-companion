package io.sengokudaikon.isn.compendium.domain.character.companion.entity

import io.sengokudaikon.isn.compendium.domain.character.ancestry.entity.VisionSense
import io.sengokudaikon.isn.compendium.persistence.character.companion.entity.AnimalCompanionSenses
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AnimalCompanionSense(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AnimalCompanionSense>(AnimalCompanionSenses)

    var animalCompanionID by AnimalCompanion referencedOn AnimalCompanionSenses.animalCompanionID
    var sense by VisionSense referencedOn AnimalCompanionSenses.sense
}