package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.Serializable

@Serializable
data class Publication(
    val license: String,
    val remaster: Boolean,
    val title: String,
)
