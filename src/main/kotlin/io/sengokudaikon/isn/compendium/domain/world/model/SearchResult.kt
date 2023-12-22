package io.sengokudaikon.isn.compendium.domain.world.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

@OptIn(ExperimentalSerializationApi::class)
@Serializable(with = SearchResult.SearchResultSerializer::class)
data class SearchResult(
    val score: Double? = null,
    val id: String? = null,
    val img: String? = null,
    val name: String? = null,
    val type: String? = null,
    val description: String? = null,
) {
    object SearchResultSerializer : KSerializer<SearchResult> {
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SearchResult") {
            element<Double?>("score")
            element<String?>("id")
            element<String?>("img")
            element<String?>("name")
            element<String?>("type")
            element<String?>("description")
        }

        override fun serialize(encoder: Encoder, value: SearchResult) {
            val jsonEncoder = encoder as? JsonEncoder
                ?: throw SerializationException("This class can be saved only by Json")
            val jsonObject = buildJsonObject {
                put("score", value.score)
                put("id", value.id)
                put("img", value.img)
                put("name", value.name)
                put("type", value.type)
                put("description", value.description)
            }
            jsonEncoder.encodeJsonElement(jsonObject)
        }

        override fun deserialize(decoder: Decoder): SearchResult {
            val jsonDecoder = decoder as? JsonDecoder
                ?: throw SerializationException("This class can be loaded only by Json")
            val jsonObject = jsonDecoder.decodeJsonElement() as? JsonObject
                ?: throw SerializationException("Expected JsonObject")
            val score = jsonObject["score"]?.jsonPrimitive?.doubleOrNull
            val id = jsonObject["_id"]?.jsonObject?.get("\$oid")?.jsonPrimitive?.contentOrNull
            val img = jsonObject["img"]?.jsonPrimitive?.contentOrNull
            val name = jsonObject["name"]?.jsonPrimitive?.contentOrNull
            val type = jsonObject["type"]?.jsonPrimitive?.contentOrNull
            val description = jsonObject["system"]?.jsonObject
                ?.get("description")?.jsonObject
                ?.get("value")?.jsonPrimitive?.contentOrNull
            return SearchResult(
                score = score,
                id = id,
                img = img,
                name = name,
                type = type,
                description = description,
            )
        }
    }
}
