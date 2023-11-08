package io.sengokudaikon.dbfinder.domain.character.classs.entity

import io.sengokudaikon.dbfinder.persistence.character.classs.entity.ClassFeatures
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClassFeat(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ClassFeat>(ClassFeatures)

    var classID by Class referencedOn ClassFeatures.classID
    var level by ClassFeatures.level
    var img by ClassFeatures.img
    var name by ClassFeatures.name
    var actionType by ClassFeatures.actionType
    var actions by ClassFeatures.actions
    var category by ClassFeatures.category
    var description by ClassFeatures.description
    var contentSrc by ClassFeatures.contentSrc
    var type by ClassFeatures.type
    var rules by ClassFeatures.rules
}
