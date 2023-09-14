package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Traits
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryTraits : KotlinxUUIDTable("char_ancestry_traits") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val trait = reference("trait_id", Traits)
}
