package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray

@Serializable
sealed class Value {

    @Serializable
    data class String(val value: kotlin.String) : Value()

    @Serializable
    data class Boolean(val value: kotlin.Boolean) : Value()

    @Serializable
    data class Int(val value: kotlin.Int) : Value()

    @Serializable
    data class StringList(val value: List<kotlin.String>) : Value()

    @Serializable
    data class IntList(val value: List<kotlin.Int>) : Value()

    @Serializable
    data class LesserGreater(val greater: List<kotlin.String>, val lesser: List<kotlin.String>) : Value()

    @Serializable
    data class Brackets(val brackets: List<Bracket>) : Value() {

        @Serializable
        data class Bracket(
            val end: kotlin.Int? = null,
            val start: kotlin.Int? = null,
            val value: kotlin.Int,
        )
    }

    object ValueSerializer : JsonContentPolymorphicSerializer<Value>(Value::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Value> {
            return when (element) {
                is JsonPrimitive -> when {
                    element.isString -> String.serializer()
                    element.booleanOrNull != null -> Boolean.serializer()
                    element.intOrNull != null -> Int.serializer()
                    else -> throw IllegalArgumentException("Unknown value type: $element")
                }

                is JsonObject -> when {
                    "greater" in element && "lesser" in element -> LesserGreater.serializer()
                    else -> String.serializer()
                }

                is JsonArray -> when {
                    element.jsonArray.all { it is JsonPrimitive && it.isString } -> StringList.serializer()
                    element.jsonArray.all { it is JsonPrimitive && it.intOrNull != null } -> IntList.serializer()
                    else -> throw IllegalArgumentException("Unknown value type: $element")
                }

                else -> throw IllegalArgumentException("Unknown value type: $element")
            }
        }
    }
}
