package io.sengokudaikon.isn.compendium.domain.spell

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer
import org.bson.types.ObjectId

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class SpellModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
    var effects: List<SpellEffectModel> = listOf()
    @OptIn(ExperimentalSerializationApi::class)
    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits?,
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) val area: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) val cost: BsonValue? = null,
        val counteraction: Boolean,
        @Serializable(with = BsonValueSerializer::class) val damage: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val defense: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val duration: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val level: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val range: BsonValue,
        val requirements: String,
        @Serializable(with = BsonValueSerializer::class) val target: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val time: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val ritual: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) val heightening: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) val overlays: BsonValue? = null,
    ) : SystemModel
}
