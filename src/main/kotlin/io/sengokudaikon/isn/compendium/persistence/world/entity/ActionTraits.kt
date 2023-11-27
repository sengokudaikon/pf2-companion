package io.sengokudaikon.isn.compendium.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object ActionTraits : KotlinxUUIDTable("action_traits") {
    val actionId = reference("action_id", Actions)
    val traitId = reference("trait_id", Traits)
}
