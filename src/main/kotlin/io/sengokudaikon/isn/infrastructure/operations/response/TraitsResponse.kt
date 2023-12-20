package io.sengokudaikon.isn.infrastructure.operations.response

import kotlinx.serialization.Serializable

@Serializable
data class TraitsResponse(
    val otherTags: List<String> = listOf(),
    val rarity: String? = null,
    val value: List<String>,
    val selected: String? = null,
)
