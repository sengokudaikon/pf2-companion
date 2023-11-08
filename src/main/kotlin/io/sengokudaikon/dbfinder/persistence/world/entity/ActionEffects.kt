package io.sengokudaikon.dbfinder.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object ActionEffects : KotlinxUUIDTable("action_effects") {
    val actionId = reference("action_id", Actions)
    val effectId = reference("effect_id", Effects)
}
