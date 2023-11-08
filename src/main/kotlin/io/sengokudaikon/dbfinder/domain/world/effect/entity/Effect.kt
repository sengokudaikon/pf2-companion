package io.sengokudaikon.dbfinder.domain.world.effect.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.EffectDurations
import io.sengokudaikon.dbfinder.persistence.world.entity.Effects
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.effect.model.Effect as ModelEffect

class Effect(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Effect>(Effects)

    var img by Effects.img
    var name by Effects.name
    var description by Effects.description
    var level by Effects.level
    var contentSrc by Effects.contentSrc
    var rarity by Effects.rarity
    val duration by EffectDuration referrersOn EffectDurations.effectId
    var rules by Effects.rules
    fun toModel(): ModelEffect {
        return ModelEffect(
            img = this.img,
            name = this.name,
            description = this.description,
            level = this.level,
            contentSrc = this.contentSrc,
            rarity = this.rarity,
            duration = this.duration.map { it.toModel() },
            rules = this.rules,
        )
    }
}
