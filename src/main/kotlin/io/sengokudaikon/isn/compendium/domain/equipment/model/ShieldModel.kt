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
data class ShieldModel(
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
    data class SystemProperty(
        val acBonus: Int,
        override val baseItem: String,
        override val containerId: String?,
        override val description: DescriptionType,
        override val hardness: Int,
        override val hp: HP,
        @Serializable(with = BsonValueSerializer::class) override val level: BsonValue,
        override val price: Price,
        override val quantity: Int,
        val runes: Runes?,
        override val size: String,
        val specific: Specific?,
        val speedPenalty: Int,
        override val traits: Traits,
        @Serializable(with = BsonValueSerializer::class) override val usage: BsonValue? = null,
        override val material: Material?,
        @Serializable(with = BsonValueSerializer::class) override val bulk: BsonValue? = null,
        override val publication: Publication,
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue?
    ) : EquipmentSystemModel {
        @Serializable
        data class Specific(
            val integrated: String?,
            val material: Material?,
            val runes: Runes
        )

        @Serializable
        data class Runes(
            val reinforcing: Int
        )
    }
}
