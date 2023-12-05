package io.sengokudaikon.isn.compendium.operations.global.response

import kotlinx.serialization.Serializable

@Serializable
data class TraitsResponse(
    val otherTags: List<String> = listOf(),
    val rarity: String? = null,
    val value: List<String>,
    val selected: String? = null,
)
