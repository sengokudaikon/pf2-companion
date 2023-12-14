package io.sengokudaikon.isn.compendium.domain.spell

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
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

    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits?,
        override val rules: List<GenericRule>,
        @Serializable(with = BsonValueSerializer::class) val ability: BsonValue,
        val area: AreaFixture?,
        @Serializable(with = BsonValueSerializer::class) val category: BsonValue,
        val components: ComponentsFixture,
        @Serializable(with = BsonValueSerializer::class) val cost: BsonValue,
        val damage: DamageContainer?,
        val overlays: Map<String, OverlayFixture>,
        @Serializable(with = BsonValueSerializer::class) val duration: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val primaryCheck: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val secondaryCheck: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val secondaryCasters: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val hasCounteractCheck: BsonValue,
        val heightening: HeighteningFixture,
        @Serializable(with = BsonValueSerializer::class) val level: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val materials: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val prepared: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val range: BsonValue,
        val save: SaveFixture,
        @Serializable(with = BsonValueSerializer::class) val spellType: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val sustained: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val target: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val time: BsonValue,
        val traditions: TraditionsFixture,
    ) : SystemModel {
        @Serializable
        data class AreaFixture(
            val details: String,
            val type: String,
            val value: Int,
        )

        @Serializable
        data class ComponentsFixture(
            @Serializable(with = BsonValueSerializer::class) val material: BsonValue? = null,
            @Serializable(with = BsonValueSerializer::class) val somatic: BsonValue,
            @Serializable(with = BsonValueSerializer::class) val verbal: BsonValue,
        )

        @Serializable
        data class HeighteningFixture(
            @Serializable(with = BsonValueSerializer::class) val damage: BsonValue,
            val interval: Int,
            val type: String,
        )

        @Serializable
        data class SaveFixture(
            val basic: String,
            val value: String,
        )

        @Suppress("ConstructorParameterNaming")
        @Serializable
        data class OverlayFixture(
            @SerialName("_id")
            @Contextual
            val id: ObjectId,
            val overlayType: String,
            val sort: Int,
            val system: SystemProperty,
        ) {
            @Serializable
            data class SystemProperty(
                val heightening: HeighteningFixture?,
                @Serializable(with = BsonValueSerializer::class) val time: BsonValue?,
                @Serializable(with = BsonValueSerializer::class) val area: BsonValue?,
            )
        }

        @Serializable
        data class DamageContainer(
            val value: Map<String, DamageFixture>,
        ) {
            @Serializable
            data class DamageFixture(
                val applyMod: Boolean,
                val type: DamageTypeFixture,
                val value: String,
            ) {
                @Serializable
                data class DamageTypeFixture(
                    val categories: List<String>,
                    val subtype: String?,
                    val value: String,
                )
            }
        }

        @Serializable
        data class TraditionsFixture(
            val custom: String,
            val values: List<String>,
        )
    }

    override fun getSerializer(): KSerializer<*> = serializer()
}
