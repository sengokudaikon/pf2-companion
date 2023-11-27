package io.sengokudaikon.isn.builder.fixtures.model.system

import kotlinx.serialization.Serializable

@Serializable
data class Languages(
    val count: Int,
    val custom: String,
    val value: List<String>,
)
