package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.Serializable

@Serializable
data class SelfEffect(
    val name: String,
    val uuid: String,
)
