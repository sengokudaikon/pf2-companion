package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryBoosts : KotlinxUUIDTable("char_ancestry_boosts") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val boostedAbility = enumerationByName<Ability>(
        "boosted_ability",
        length = 20,
    ).default(Ability.Anything)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
