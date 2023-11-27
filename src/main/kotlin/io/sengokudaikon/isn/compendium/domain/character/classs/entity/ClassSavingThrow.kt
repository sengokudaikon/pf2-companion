package io.sengokudaikon.isn.compendium.domain.character.classs.entity

import io.sengokudaikon.isn.compendium.persistence.character.classs.entity.ClassSavingThrows
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClassSavingThrow(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ClassSavingThrow>(ClassSavingThrows)

    var classID by Class referencedOn ClassSavingThrows.classID
    var savingThrow by ClassSavingThrows.savingThrow
    var proficiency by ClassSavingThrows.proficiency
}
