package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.Serializable

@Serializable
data class Frequency(
    val max: Int,
    val per: String,
)
