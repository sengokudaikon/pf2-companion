package io.sengokudaikon.isn.builder.fixtures.model.system

import kotlinx.serialization.Serializable

@Serializable
data class Traits(
    val otherTags: List<String> = listOf(),
    val rarity: String,
    val value: List<String>,
)
