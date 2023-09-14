package io.sengokudaikon.dbfinder.domain.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassSavingThrows
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClassSavingThrow(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ClassSavingThrow>(ClassSavingThrows)
    var classID by ClassSavingThrows.classID
    var savingThrow by ClassSavingThrows.savingThrow
    var proficiency by ClassSavingThrows.proficiency
}
