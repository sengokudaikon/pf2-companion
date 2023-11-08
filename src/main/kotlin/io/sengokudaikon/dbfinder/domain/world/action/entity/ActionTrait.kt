package io.sengokudaikon.dbfinder.domain.world.action.entity

import io.sengokudaikon.dbfinder.domain.world.global.entity.Trait
import io.sengokudaikon.dbfinder.persistence.world.entity.ActionTraits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ActionTrait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ActionTrait>(ActionTraits)

    var actionID by Action referencedOn ActionTraits.actionId
    var trait by Trait referencedOn ActionTraits.traitId
}
