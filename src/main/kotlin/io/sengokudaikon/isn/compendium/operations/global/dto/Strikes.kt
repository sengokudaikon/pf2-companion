package io.sengokudaikon.isn.compendium.operations.global.dto

import kotlinx.serialization.Serializable

@Serializable
data class Strikes(
    val ability: String? = null,
    val baseType: String? = null,
    val category: String? = null,
    val damage: Damage? = null,
    val modifier: Int? = null,
    val traits: List<String> = emptyList(),
)
