package io.sengokudaikon.isn.builder.fixtures.dto

import kotlinx.serialization.Serializable

@Serializable
data class JsonItem(
    val img: String? = null,
    val level: Int = 1,
    val name: String? = null,
    val uuid: String,
)
