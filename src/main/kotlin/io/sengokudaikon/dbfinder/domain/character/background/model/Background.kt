package io.sengokudaikon.dbfinder.domain.character.background.model

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AbilityBoost
import io.sengokudaikon.dbfinder.domain.character.feat.model.Item
import io.sengokudaikon.dbfinder.domain.world.model.Rule
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import kotlinx.serialization.Serializable

@Serializable
data class Background(
    val id: String,
    val name: String,
    val rarity: io.sengokudaikon.dbfinder.infrastructure.enums.Rarity,
    val description: String,
    val code: String,
    val boosts: List<AbilityBoost> = listOf(),
    val items: List<Item> = listOf(),
    val rules: List<Rule> = listOf(),
    val trainedLore: String? = null,
    val trainedSkills: List<Skills> = listOf(),
)
