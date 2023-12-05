package io.sengokudaikon.isn.compendium.domain.equipment.dto

import kotlinx.serialization.Serializable

@Serializable
data class Price(
    val value: Map<String, Int>,
)
