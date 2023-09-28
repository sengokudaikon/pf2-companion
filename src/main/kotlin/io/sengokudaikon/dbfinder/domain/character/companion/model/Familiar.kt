package io.sengokudaikon.dbfinder.domain.character.companion.model

import kotlinx.serialization.Serializable

@Serializable
data class Familiar(
    val name: String,
    val description: String,
    val hp: Int,
    val rarity: String,
    val type: String,
    val abilities: List<FamiliarAbility>,
    val homebrewID: String? = null,
)
