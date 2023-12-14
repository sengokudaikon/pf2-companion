package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

interface Mapper<T : Model> {
    fun toResponse(model: T): Response<T>
}
fun String?.extractValue(): String? {
    return this?.let {
        val jsonObject = Json.parseToJsonElement(it) as JsonObject
        val valueElement = jsonObject["value"]
        when {
            valueElement is JsonArray && valueElement.isEmpty() == true -> ""
            valueElement is JsonNull || valueElement?.jsonPrimitive == null -> ""
            else -> valueElement.jsonPrimitive.content
        }
    }
}
