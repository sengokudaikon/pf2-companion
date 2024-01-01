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
@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ConsumableModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
): Model {
    @Serializable
    data class SystemProperty(
        override val baseItem: String?,
        override val containerId: String?,
        override val hardness: Int,
        override val hp: HP,
        @Serializable(with = BsonValueSerializer::class)
        override val level: BsonValue,
        override val material: Material?,
        override val price: Price,
        override val quantity: Int,
        override val size: String,
        @Serializable(with = BsonValueSerializer::class)
        override val usage: BsonValue,
        @Serializable(with = BsonValueSerializer::class)
        override val bulk: BsonValue?,
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits?,
        @Serializable(with = BsonValueSerializer::class)
        override val rules: BsonValue?,
        val category: String,
        val uses: Uses,
    ): EquipmentSystemModel{
        @Serializable
        data class Uses(val value: Int?, val autoDestroy: Boolean, val max: Int?)
    }
}
