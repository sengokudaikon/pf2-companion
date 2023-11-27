package io.sengokudaikon.isn.compendium.domain.character.ancestry.model

import io.sengokudaikon.isn.compendium.domain.world.global.model.Trait
import kotlinx.serialization.Serializable

@Serializable
data class AncestryFeature(
    val name: String,
    val description: String,
    val img: String,
    val level: Int,
    val traits: List<Trait> = listOf(),
)
