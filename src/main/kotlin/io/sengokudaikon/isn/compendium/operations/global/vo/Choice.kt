package io.sengokudaikon.isn.compendium.operations.global.vo

import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val label: String? = null,
    val value: String? = null,
)
