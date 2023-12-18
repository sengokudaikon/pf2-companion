package io.sengokudaikon.isn.compendium.domain.equipment.model

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.EquipmentSystemModel
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
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
data class EquipmentModel(
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
        override val description: DescriptionType,
        override val rules: List<GenericRule>,
        override val traits: Traits,
        override val publication: Publication,
        override val baseItem: String?,
        override val containerId: String?,
        @Serializable(with = BsonValueSerializer::class) override val equippedBulk: BsonValue,
        override val hardness: Int,
        override val hp: HP,
        @Serializable(with = BsonValueSerializer::class) override val level: BsonValue,
        override val material: Material?,
        @Serializable(with = BsonValueSerializer::class) override val negateBulk: BsonValue,
        override val price: Price,
        override val quantity: Int,
        override val size: String,
        override val stackGroup: String?,
        @Serializable(with = BsonValueSerializer::class) override val usage: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val weight: BsonValue,
    ) : EquipmentSystemModel

    override fun getSerializer(): KSerializer<*> = serializer()
}
