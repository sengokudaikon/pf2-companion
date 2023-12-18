package io.sengokudaikon.isn.compendium.enums

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = BoundedIntSerializer::class)
class BoundedInt(private var initial: Int = 0, private var upperLimit: Int = 4) {
    var value = initial
        set(value) {
            field = value.coerceIn(initial..upperLimit)
        }
}

object BoundedIntSerializer : KSerializer<BoundedInt> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BoundedInt", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: BoundedInt) = encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): BoundedInt = BoundedInt(decoder.decodeInt(), 4)
}
