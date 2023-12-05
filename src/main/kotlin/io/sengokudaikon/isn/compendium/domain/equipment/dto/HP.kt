package io.sengokudaikon.isn.compendium.domain.equipment.dto

import kotlinx.serialization.Serializable

@Serializable
data class HP(
    val max: Int,
    val value: Int,
)
