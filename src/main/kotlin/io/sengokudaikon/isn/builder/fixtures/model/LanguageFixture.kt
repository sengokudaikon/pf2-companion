package io.sengokudaikon.isn.builder.fixtures.model

import kotlinx.serialization.Serializable

@Serializable
data class LanguageFixture(
    val name: String,
    val description: String,
    val contentSrc: String,
)
