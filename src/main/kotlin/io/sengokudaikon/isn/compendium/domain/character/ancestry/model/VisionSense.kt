package io.sengokudaikon.isn.compendium.domain.character.ancestry.model

import io.sengokudaikon.isn.compendium.enums.VisionType
import kotlinx.serialization.Serializable

@Serializable
data class VisionSense(
    val name: VisionType,
    val range: Int,
    val isPrecise: Boolean,
)
