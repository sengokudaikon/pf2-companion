package io.sengokudaikon.fixtureloader.fixtures.model.system

import kotlinx.serialization.Serializable

@Serializable
data class Publication(
    val license: String,
    val remaster: Boolean,
    val title: String,
)
