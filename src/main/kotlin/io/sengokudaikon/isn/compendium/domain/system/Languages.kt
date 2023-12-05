package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.Serializable

@Serializable
data class Languages(
    val count: Int? = null,
    val custom: String,
    val value: List<String>,
)
