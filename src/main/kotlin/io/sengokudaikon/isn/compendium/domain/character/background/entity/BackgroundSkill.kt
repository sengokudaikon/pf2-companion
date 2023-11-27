package io.sengokudaikon.isn.compendium.domain.character.background.entity

import io.sengokudaikon.isn.compendium.persistence.character.background.entity.BackgroundSkills
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BackgroundSkill(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<BackgroundSkill>(BackgroundSkills)

    var backgroundId by Background referencedOn BackgroundSkills.background
    var skillId by BackgroundSkills.skill
    var proficiency by BackgroundSkills.proficiency
}
