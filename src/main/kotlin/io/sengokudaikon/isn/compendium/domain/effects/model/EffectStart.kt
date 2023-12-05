package io.sengokudaikon.isn.compendium.domain.effects.model

import kotlinx.serialization.Serializable

@Serializable
data class EffectStart(
    val initiative: String? = null,
    val value: Int? = null,
)
