package io.sengokudaikon.isn.compendium.domain.character.feat.model

import io.sengokudaikon.isn.compendium.enums.Skills
import kotlinx.serialization.Serializable

@Serializable
data class Feat(
    val name: String,
    val description: String,
    val rarity: io.sengokudaikon.isn.compendium.enums.Rarity,
    val actions: io.sengokudaikon.isn.compendium.enums.ActionTypes,
    val type: io.sengokudaikon.isn.compendium.enums.FeatTypes,
    val requirements: String,
    val level: Int,
    val frequency: String,
    val trigger: String,
    val canSelectMultiple: Boolean,
    val isDefault: Boolean,
    val skillId: Skills,
    val proficiencyId: io.sengokudaikon.isn.compendium.enums.Proficiency,
    val contentSrc: String,
) : Item
