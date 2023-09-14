package io.sengokudaikon.dbfinder.domain.character.feat.model

import io.sengokudaikon.dbfinder.persistence.enums.ActionTypes
import io.sengokudaikon.dbfinder.persistence.enums.Proficiency
import io.sengokudaikon.dbfinder.persistence.enums.Skills
import kotlinx.serialization.Serializable

@Serializable
data class Feat(
    val name: String,
    val description: String,
    val rarity: String,
    val actions: ActionTypes,
    val type: String,
    val requirements: String,
    val level: Int,
    val frequency: String,
    val trigger: String,
    val canSelectMultiple: Boolean,
    val isDefault: Boolean,
    val skillId: Skills,
    val proficiencyId: Proficiency,
    val contentSrc: String,
    val homebrewID: Int?,
    val version: String,
)
