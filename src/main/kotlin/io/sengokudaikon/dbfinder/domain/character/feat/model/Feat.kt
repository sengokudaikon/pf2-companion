package io.sengokudaikon.dbfinder.domain.character.feat.model

import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import kotlinx.serialization.Serializable

@Serializable
data class Feat(
    val name: String,
    val description: String,
    val rarity: io.sengokudaikon.dbfinder.infrastructure.enums.Rarity,
    val actions: io.sengokudaikon.dbfinder.infrastructure.enums.ActionTypes,
    val type: io.sengokudaikon.dbfinder.infrastructure.enums.FeatTypes,
    val requirements: String,
    val level: Int,
    val frequency: String,
    val trigger: String,
    val canSelectMultiple: Boolean,
    val isDefault: Boolean,
    val skillId: Skills,
    val proficiencyId: io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency,
    val contentSrc: String,
) : Item
