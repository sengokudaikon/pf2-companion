package io.sengokudaikon.isn.builder.fixtures.model.system

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val img: String,
    val level: Int,
    val name: String,
    val uuid: String,
)
