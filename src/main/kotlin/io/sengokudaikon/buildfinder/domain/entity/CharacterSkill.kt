package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterSkills
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterSkill(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterSkill>(CharacterSkills)

    var character by Character referencedOn CharacterSkills.characterID
    var skill by CharacterSkills.skillID
    var value by CharacterSkills.value
    var currentProficiency by CharacterSkills.currentProficiency
}
