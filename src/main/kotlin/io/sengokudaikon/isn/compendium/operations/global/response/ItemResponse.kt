package io.sengokudaikon.isn.compendium.operations.global.response

import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val img: String? = null,
    val level: String? = null,
    val name: String? = null,
    val uuid: String? = null,
)
