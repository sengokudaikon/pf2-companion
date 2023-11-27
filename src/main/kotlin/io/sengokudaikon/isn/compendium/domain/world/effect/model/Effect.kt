package io.sengokudaikon.isn.compendium.domain.world.effect.model

import io.sengokudaikon.isn.compendium.enums.Rarity
import kotlinx.serialization.Serializable

@Serializable
data class Effect(
    val img: String,
    val name: String,
    var description: String,
    var level: Int,
    var contentSrc: String,
    var rarity: Rarity,
    val duration: List<Duration>,
    val rules: String?,
)
