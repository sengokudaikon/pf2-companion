package io.sengokudaikon.isn.compendium.domain.equipment.model

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.EquipmentSystemModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
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
data class ArmorModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
    @OptIn(ExperimentalSerializationApi::class)
    @Serializable
    @Suppress("LongParameterList")
    class SystemProperty(
        override val description: DescriptionType,
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        override val traits: Traits,
        override val publication: Publication,
        override val baseItem: String?,
        override val containerId: String?,
        override val hardness: Int,
        override val hp: HP,
        override val material: Material?,
        override val price: Price,
        override val quantity: Int,
        override val size: String,
        val group: String?=null,
        val specific: Specific?,
        val speedPenalty: Int?,
        val acBonus: Int,
        val dexCap: Int,
        val category: String,
        val checkPenalty: Int,
        val strength: Int? = null,
        @Serializable(with = BsonValueSerializer::class) override val level: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val usage: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) override val bulk: BsonValue,
    ) : EquipmentSystemModel {
        @Serializable
        data class Specific(
            val material: Material?,
            val runes: Runes?,
            val value: Boolean? = null,
        ) {
            @Serializable
            data class Runes(
                val potency: Int,
                val resilient: Int,
                val property: List<String> = listOf(),
            )
        }
    }
}
