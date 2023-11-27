package io.sengokudaikon.isn.builder.fixtures.model.system

import kotlinx.serialization.Serializable

@Serializable
data class Frequency(
    val max: Int,
    val per: String,
)
