package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.compendium.operations.global.response.ItemResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Item(
    val img: String,
    @Serializable(with = BsonValueSerializer::class) val level: BsonValue? = null,
    val name: String,
    val uuid: String,
) {
    fun toResponse(): ItemResponse {
        return ItemResponse(
            img = img,
            level = level?.transform(),
            name = name,
            uuid = uuid,
        )
    }
}
