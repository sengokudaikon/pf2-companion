package io.sengokudaikon.dbfinder.domain.character.classs.model

import kotlinx.serialization.Serializable

@Serializable
data class Class(
    val name: String,
    val description: String,
    val hitPoints: Int,
    val keyAttribute: String,
    val rarity: String,
    val classDC: Int,
    val proficiencies: Map<String, String>,
    val savingThrows: Map<String, String>,
    val weapons: Map<String, String>,
    val additionalWeapons: List<String> = emptyList(),
    val armours: Map<String, String>,
    val homebrewID: String? = null,
)
