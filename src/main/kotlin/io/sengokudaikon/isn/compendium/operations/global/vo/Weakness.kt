package io.sengokudaikon.isn.compendium.operations.global.vo

import kotlinx.serialization.Serializable

@Serializable
data class Weakness(
    val type: String? = null,
    val value: Int? = null,
)
