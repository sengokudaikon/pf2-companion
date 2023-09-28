package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import io.sengokudaikon.dbfinder.infrastructure.enums.Choice
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import kotlinx.serialization.Serializable

@Serializable
data class BackgroundFixture(
    val img: String,
    val name: String,
    val boosts: List<Choice<Ability>>,
    val description: String,
    val items: List<JsonItem>,
    val rules: List<JsonRule>,
    val source: String,
    val trainedLore: String,
    val trainedSkills: List<Choice<Skills>>,
    val rarity: Rarity,
    val traits: List<Trait>,
)
