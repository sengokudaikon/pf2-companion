package io.sengokudaikon.dbfinder.domain.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.character.companion.entity.AnimalCompanionSkills
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AnimalCompanionSkill(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AnimalCompanionSkill>(AnimalCompanionSkills)

    var animalCompanion by AnimalCompanion referencedOn AnimalCompanionSkills.animalCompanionID
    var skill by AnimalCompanionSkills.skill
    var proficiency by AnimalCompanionSkills.proficiency
}
