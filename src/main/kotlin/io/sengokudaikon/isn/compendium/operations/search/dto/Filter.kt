package io.sengokudaikon.isn.compendium.operations.search.dto

import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    val key: FilterType,
    val comparison: Comparison,
    val value: String,
)
