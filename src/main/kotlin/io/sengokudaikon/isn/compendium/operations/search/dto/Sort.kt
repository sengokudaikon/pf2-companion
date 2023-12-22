package io.sengokudaikon.isn.compendium.operations.search.dto

import kotlinx.serialization.Serializable

@Serializable
data class Sort(val field: SortType, val ascending: Boolean)