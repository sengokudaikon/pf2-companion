package io.sengokudaikon.isn.compendium.domain.character.companion.model

import kotlinx.serialization.Serializable

@Serializable
data class FamiliarAbility(
    val name: String,
    val description: String,
    val prerequisites: String,
    val requirements: String,
)
