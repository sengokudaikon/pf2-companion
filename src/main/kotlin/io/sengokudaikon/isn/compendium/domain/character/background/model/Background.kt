package io.sengokudaikon.isn.compendium.domain.character.background.model

import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.AbilityBoost
import io.sengokudaikon.isn.compendium.domain.character.feat.model.Item
import io.sengokudaikon.isn.compendium.domain.world.global.model.Trait
import io.sengokudaikon.isn.compendium.enums.Skills
import kotlinx.serialization.Serializable

@Serializable
data class Background(
    val id: String,
    val name: String,
    val rarity: io.sengokudaikon.isn.compendium.enums.Rarity,
    val description: String,
    val code: String,
    val boosts: List<AbilityBoost> = listOf(),
    val items: List<Item> = listOf(),
    val rules: String? = null,
    val traits: List<Trait> = listOf(),
    val trainedLore: String? = null,
    val trainedSkills: List<Skills> = listOf(),
)
