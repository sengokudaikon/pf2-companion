package io.sengokudaikon.dbfinder.fixtures

import kotlinx.serialization.Serializable

@Serializable
data class TraitFixture(
    val name: String,
    val description: String,
    val contentSrc: String,
    val type: String,
)
