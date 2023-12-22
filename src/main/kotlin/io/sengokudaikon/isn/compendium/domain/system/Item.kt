package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val img: String,
    val level: Int? = null,
    val name: String,
    val uuid: String,
)
