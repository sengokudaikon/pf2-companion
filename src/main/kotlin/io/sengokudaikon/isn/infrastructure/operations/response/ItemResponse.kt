package io.sengokudaikon.isn.infrastructure.operations.response

import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val img: String? = null,
    val level: String? = null,
    val name: String? = null,
    val uuid: String? = null,
)
