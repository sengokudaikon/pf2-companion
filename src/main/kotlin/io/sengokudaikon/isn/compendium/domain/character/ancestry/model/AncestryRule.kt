package io.sengokudaikon.isn.compendium.domain.character.ancestry.model

import kotlinx.serialization.Serializable

@Serializable
data class AncestryRule(
    val name: String,
    val description: String,
)
