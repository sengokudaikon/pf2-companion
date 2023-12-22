package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.infrastructure.operations.response.ItemResponse
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val img: String,
    val level: Int? = null,
    val name: String,
    val uuid: String,
) {
    fun toResponse(): ItemResponse {
        return ItemResponse(
            img = img,
            level = level,
            name = name,
            uuid = uuid,
        )
    }
}
