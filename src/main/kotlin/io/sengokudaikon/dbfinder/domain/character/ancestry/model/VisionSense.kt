package io.sengokudaikon.dbfinder.domain.character.ancestry.model

import io.sengokudaikon.dbfinder.infrastructure.enums.VisionType
import kotlinx.serialization.Serializable

@Serializable
data class VisionSense(
    val name: VisionType,
    val range: Int,
    val isPrecise: Boolean,
)
