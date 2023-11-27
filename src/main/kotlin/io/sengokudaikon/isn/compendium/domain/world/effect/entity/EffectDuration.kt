package io.sengokudaikon.isn.compendium.domain.world.effect.entity

import io.sengokudaikon.isn.compendium.domain.world.effect.model.Duration
import io.sengokudaikon.isn.compendium.persistence.world.entity.EffectDurations
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EffectDuration(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<EffectDuration>(EffectDurations)

    var effect by Effect referencedOn EffectDurations.effectId
    var expiry by EffectDurations.expiry
    var sustained by EffectDurations.sustained
    var unit by EffectDurations.unit
    var value by EffectDurations.value
    fun toModel(): Duration {
        return Duration(
            expiry = this.expiry,
            sustained = this.sustained,
            unit = this.unit.toString(),
            value = this.value,
        )
    }
}
