package io.sengokudaikon.isn.builder.fixtures.model

import kotlinx.serialization.Serializable

@Serializable
data class TraitFixture(
    val name: String,
    val description: String,
    val contentSrc: String,
    val type: String,
)
