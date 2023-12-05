package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.compendium.operations.global.response.TraitsResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Traits(
    val otherTags: List<String> = listOf(),
    val rarity: String? = null,
    val value: List<String>,
    @Serializable(with = BsonValueSerializer::class) val selected: BsonValue? = null,
) {
    fun toResponse(): TraitsResponse {
        return TraitsResponse(
            otherTags = otherTags,
            rarity = rarity,
            value = value,
            selected = selected?.transform(),
        )
    }
}
