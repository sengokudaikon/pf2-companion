package io.sengokudaikon.isn.compendium.domain.character.classs.entity

import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.persistence.character.classs.entity.ClassArmours as EntityClassArmour

class ClassArmour(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ClassArmour>(EntityClassArmour)

    var classID by Class referencedOn EntityClassArmour.classID
    var armour by EntityClassArmour.armour
    var proficiency by EntityClassArmour.proficiency
}
