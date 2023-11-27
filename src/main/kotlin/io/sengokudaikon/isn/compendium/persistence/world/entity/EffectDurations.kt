package io.sengokudaikon.isn.compendium.persistence.world.entity

import io.sengokudaikon.isn.compendium.domain.world.effect.entity.DurationUnit
import kotlinx.uuid.exposed.KotlinxUUIDTable

object EffectDurations : KotlinxUUIDTable("effect_durations") {
    val effectId = reference("effect_id", Effects)
    val expiry = varchar("expiry", 100)
    val sustained = bool("sustained")
    val unit = enumerationByName<DurationUnit>("unit", 10)
    val value = integer("value")
}
