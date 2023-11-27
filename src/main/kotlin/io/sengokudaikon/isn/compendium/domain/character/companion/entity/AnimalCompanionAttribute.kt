package io.sengokudaikon.isn.compendium.domain.character.companion.entity

import io.sengokudaikon.isn.compendium.persistence.character.companion.entity.AnimalCompanionAttributes
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AnimalCompanionAttribute(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AnimalCompanionAttribute>(AnimalCompanionAttributes)

    var animalCompanion by AnimalCompanion referencedOn AnimalCompanionAttributes.animalCompanionID
    var attribute by AnimalCompanionAttributes.attribute
    var value by AnimalCompanionAttributes.value
}
