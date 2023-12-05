package io.sengokudaikon.isn.compendium.operations.global.vo

import kotlinx.serialization.Serializable

@Serializable
data class SubOption(
    val label: String? = null,
    val value: String? = null,
)
