package io.sengokudaikon.isn.builder.fixtures.model.system

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

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
    data class Brackets(val brackets: List<Bracket>) : Value() {

        @Serializable
        data class Bracket(
            val end: kotlin.Int? = null,
            val start: kotlin.Int? = null,
            val value: kotlin.Int,
        )
    }

    object ValTypeSerializer : KSerializer<Value> {
        override val descriptor: SerialDescriptor =
            buildClassSerialDescriptor("ValType") {
                element<String>("string")
                element<Boolean>("bool")
                element<Int>("int")
                element<Brackets>("brackets")
            }

        override fun serialize(encoder: Encoder, value: Value) {
            return when (value) {
                is String -> encoder.encodeString(value.value)
                is Boolean -> encoder.encodeBoolean(value.value)
                is Int -> encoder.encodeInt(value.value)
                is Brackets -> encoder.encodeSerializableValue(
                    ListSerializer(Brackets.Bracket.serializer()),
                    value.brackets,
                )

                is IntList -> encoder.encodeSerializableValue(
                    ListSerializer(kotlin.Int.serializer()),
                    value.value,
                )

                is StringList -> encoder.encodeSerializableValue(
                    ListSerializer(kotlin.String.serializer()),
                    value.value,
                )
            }
        }

        override fun deserialize(decoder: Decoder): Value {
            val jsonDecoder = (decoder as JsonDecoder)
            val tree = jsonDecoder.decodeJsonElement()
            return when {
                tree.jsonObject.containsKey("string")
                -> String(tree.jsonObject["string"]!!.jsonPrimitive.content)

                tree.jsonObject.containsKey("bool")
                -> Boolean(tree.jsonObject["bool"]!!.jsonPrimitive.boolean)

                tree.jsonObject.containsKey("int") -> Int(tree.jsonObject["int"]!!.jsonPrimitive.int)
                tree.jsonObject.containsKey("brackets") -> Brackets(
                    tree.jsonObject["brackets"]!!.jsonArray.map { jsonElement ->
                        jsonDecoder.json.decodeFromJsonElement(Brackets.Bracket.serializer(), jsonElement)
                    },
                )

                else -> throw SerializationException("Unrecognized type")
            }
        }
    }
}
