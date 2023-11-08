package io.sengokudaikon.fixtureloader.fixtures.model.system

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
sealed class ValueType {

    @Serializable
    data class StringValue(val value: String) : ValueType()

    @Serializable
    data class BooleanValue(val value: Boolean) : ValueType()

    @Serializable
    data class IntValue(val value: Int) : ValueType()

    @Serializable
    data class StringListValue(val value: List<String>) : ValueType()

    @Serializable
    data class IntListValue(val value: List<Int>) : ValueType()

    @Serializable
    data class BracketValue(val brackets: List<Bracket>) : ValueType() {

        @Serializable
        data class Bracket(
            val end: Int? = null,
            val start: Int? = null,
            val value: Int,
        )
    }

    object ValTypeSerializer : KSerializer<ValueType> {
        override val descriptor: SerialDescriptor =
            buildClassSerialDescriptor("ValType") {
                element<StringValue>("string")
                element<BooleanValue>("bool")
                element<IntValue>("int")
                element<BracketValue>("brackets")
            }

        override fun serialize(encoder: Encoder, value: ValueType) {
            return when (value) {
                is StringValue -> encoder.encodeString(value.value)
                is BooleanValue -> encoder.encodeBoolean(value.value)
                is IntValue -> encoder.encodeInt(value.value)
                is BracketValue -> encoder.encodeSerializableValue(
                    ListSerializer(BracketValue.Bracket.serializer()),
                    value.brackets,
                )

                is IntListValue -> encoder.encodeSerializableValue(
                    ListSerializer(Int.serializer()),
                    value.value,
                )

                is StringListValue -> encoder.encodeSerializableValue(
                    ListSerializer(String.serializer()),
                    value.value,
                )
            }
        }

        override fun deserialize(decoder: Decoder): ValueType {
            val jsonDecoder = (decoder as JsonDecoder)
            val tree = jsonDecoder.decodeJsonElement()
            return when {
                tree.jsonObject.containsKey("string")
                -> StringValue(tree.jsonObject["string"]!!.jsonPrimitive.content)

                tree.jsonObject.containsKey("bool")
                -> BooleanValue(tree.jsonObject["bool"]!!.jsonPrimitive.boolean)

                tree.jsonObject.containsKey("int") -> IntValue(tree.jsonObject["int"]!!.jsonPrimitive.int)
                tree.jsonObject.containsKey("brackets") -> BracketValue(
                    tree.jsonObject["brackets"]!!.jsonArray.map { jsonElement ->
                        jsonDecoder.json.decodeFromJsonElement(BracketValue.Bracket.serializer(), jsonElement)
                    },
                )

                else -> throw SerializationException("Unrecognized type")
            }
        }
    }
}
