package io.sengokudaikon.isn.compendium.domain.effects.model

import kotlinx.serialization.Serializable

@Serializable
data class EffectDuration(
    val expiry: String? = null,
    val sustained: Boolean,
    val unit: String,
    val value: Int,
)
