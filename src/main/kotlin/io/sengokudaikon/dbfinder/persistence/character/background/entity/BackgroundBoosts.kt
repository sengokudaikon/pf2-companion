package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import kotlinx.uuid.exposed.KotlinxUUIDTable

object BackgroundBoosts : KotlinxUUIDTable("background_boosts") {
    val backgroundId = reference("background_id", Backgrounds)
    val boostedAbility = enumerationByName<Ability>(
        "boosted_ability",
        length = 20,
    )
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
