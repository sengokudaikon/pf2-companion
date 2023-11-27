package io.sengokudaikon.isn.compendium.domain.character.companion.model

import kotlinx.serialization.Serializable

@Serializable
data class AnimalCompanion(
    val name: String,
    val rarity: String,
    val description: String,
    val size: String,
    val hp: Int,
    val ac: Int,
    val speed: Int,
    val senses: List<String>,
    val attributes: Map<Int, String>,
    val weapons: List<String>,
    val skills: Map<String, String>,
)
