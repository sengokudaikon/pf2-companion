package io.sengokudaikon.dbfinder.domain.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassSkills
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClassSkill(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ClassSkill>(ClassSkills)
    var classID by ClassSkills.classID
    var skillID by ClassSkills.skillID
    var proficiency by ClassSkills.proficiency
}
