package io.sengokudaikon.isn.compendium.operations.global.dto

import kotlinx.serialization.Serializable

@Serializable
data class Damage(
    val damageType: String? = null,
    val dice: Int? = null,
    val die: String? = null,
    val modifier: Int? = null,
)
