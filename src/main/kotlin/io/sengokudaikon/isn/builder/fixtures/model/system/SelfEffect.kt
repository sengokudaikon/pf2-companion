package io.sengokudaikon.isn.builder.fixtures.model.system

import kotlinx.serialization.Serializable

@Serializable
data class SelfEffect(
    val name: String,
    val uuid: String,
)
