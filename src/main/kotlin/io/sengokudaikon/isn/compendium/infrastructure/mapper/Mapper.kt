package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.bson.BsonArray
import org.bson.BsonDocument
import org.bson.json.JsonMode
import org.bson.json.JsonWriterSettings
import org.koin.mp.KoinPlatform.getKoin

interface Mapper<T : Model> {
    fun toResponse(model: T): Response<out T>
}

fun String.escapeBackslashes(): String {
    return this.replace("\\", "\\\\")
}

@Suppress("SwallowedException", "ReturnCount", "NestedBlockDepth")
fun String?.extractValue(): JsonElement? {
    return this?.let {
        try {
            val jsonElement = Json.parseToJsonElement(it)
            if (jsonElement is JsonObject && jsonElement.keys.size == 1 && jsonElement.containsKey("value")) {
                if (jsonElement["value"] is JsonPrimitive) return jsonElement["value"]?.jsonPrimitive
                if (jsonElement["value"] is JsonObject) return jsonElement["value"]?.jsonObject
                if (jsonElement["value"] is JsonArray) return jsonElement["value"]?.jsonArray
            }
            return jsonElement
        } catch (e: SerializationException) {
            JsonPrimitive(it)
        } catch (e: StackOverflowError) {
            JsonNull
        }
    }
}

fun rulesToJson(bsonValue: BsonArray): JsonElement {
    val elements = mutableListOf<JsonElement>()
    bsonValue.forEach {
        when (it) {
            is BsonDocument -> {
                val json: String =
                    it.toJson(JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).indent(true).build())
                val jsonElement = Json.parseToJsonElement(json)
                elements.add(jsonElement)
            }

            else -> elements.add(JsonPrimitive(it.toString()))
        }
    }
    return JsonArray(elements)
}

class ModelMapper {
    fun toResponse(model: Model): Response<out Model> {
        val classname = model::class.simpleName?.substringBefore("Model")
        val mapperClass = Class.forName("io.sengokudaikon.isn.compendium.infrastructure.mapper.${classname}Mapper")
        val mapper = getKoin().get(mapperClass.kotlin) as Mapper<Model>
        return mapper.toResponse(model)
    }
}
