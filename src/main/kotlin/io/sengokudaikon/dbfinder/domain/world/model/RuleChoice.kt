package io.sengokudaikon.dbfinder.domain.world.model

import kotlinx.serialization.Serializable

@Serializable
data class RuleChoice(
    val name: String,
    val value: String,
)
