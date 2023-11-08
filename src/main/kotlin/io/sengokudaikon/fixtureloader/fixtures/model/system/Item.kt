package io.sengokudaikon.fixtureloader.fixtures.model.system

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val img: String,
    val level: Int,
    val name: String,
    val uuid: String,
)
