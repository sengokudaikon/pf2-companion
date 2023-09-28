package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Actions
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.model.Action as ModelAction

class Action(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Action>(Actions)

    val name by Actions.name
    val image by Actions.image
    val description by Actions.description
    val actionType by Actions.actionType
    val actionNumber by Actions.actionNumber
    val category by Actions.category
    val frequency by Actions.frequency
    val requirements by Actions.requirements
    val contentSrc by Actions.contentSrc
    val trigger by Actions.trigger

    fun toModel(): ModelAction {
        return ModelAction(
            name = this.name,
            image = this.image,
            description = this.description,
            actionType = this.actionType.name,
            actionNumber = this.actionNumber,
            category = this.category.name,
            frequency = this.frequency,
            requirements = this.requirements,
            contentSrc = this.contentSrc,
            trigger = this.trigger,
        )
    }
}
