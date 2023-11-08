package io.sengokudaikon.dbfinder.domain.world.action.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.ActionEffects
import io.sengokudaikon.dbfinder.persistence.world.entity.ActionTraits
import io.sengokudaikon.dbfinder.persistence.world.entity.Actions
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.action.model.Action as ModelAction

class Action(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Action>(Actions)

    var name by Actions.name
    var image by Actions.image
    var description by Actions.description
    var actionType by Actions.actionType
    var actionNumber by Actions.actionNumber
    var category by Actions.category
    var frequency by Actions.frequency
    var requirements by Actions.requirements
    var contentSrc by Actions.contentSrc
    var trigger by Actions.trigger
    val effects by ActionEffect referrersOn ActionEffects.actionId
    val traits by ActionTrait referrersOn ActionTraits.actionId
    var rules by Actions.rules

    fun toModel(): ModelAction {
        return ModelAction(
            name = this.name,
            image = this.image,
            description = this.description,
            actionType = this.actionType,
            actionNumber = this.actionNumber,
            category = this.category,
            frequency = this.frequency,
            requirements = this.requirements,
            contentSrc = this.contentSrc,
            trigger = this.trigger,
        )
    }
}
