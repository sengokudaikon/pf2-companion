package io.sengokudaikon.isn.compendium.domain.world.global.model

import kotlinx.serialization.Serializable

@Serializable
data class RuleChoice(
    val name: String,
    val value: String,
)
