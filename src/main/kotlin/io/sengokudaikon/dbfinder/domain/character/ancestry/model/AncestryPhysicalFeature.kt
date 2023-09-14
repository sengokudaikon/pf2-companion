package io.sengokudaikon.dbfinder.domain.character.ancestry.model

import io.sengokudaikon.dbfinder.domain.world.model.Trait
import kotlinx.serialization.Serializable

@Serializable
data class AncestryPhysicalFeature(
    val name: String,
    val description: String,
    val img: String,
    val level: Int,
    val traits: List<Trait> = listOf(),
)
