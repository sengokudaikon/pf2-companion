package io.sengokudaikon.isn.compendium.domain.world.action.entity

import io.sengokudaikon.isn.compendium.domain.world.effect.entity.Effect
import io.sengokudaikon.isn.compendium.persistence.world.entity.ActionEffects
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ActionEffect(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<ActionEffect>(ActionEffects)

    var actionId by Action referencedOn ActionEffects.actionId
    var effect by Effect referencedOn ActionEffects.effectId
}
