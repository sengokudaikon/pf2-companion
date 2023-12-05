package io.sengokudaikon.isn.compendium.domain.equipment.dto

import kotlinx.serialization.Serializable

@Serializable
data class Material(
    val grade: String?,
    val type: String?,
)
