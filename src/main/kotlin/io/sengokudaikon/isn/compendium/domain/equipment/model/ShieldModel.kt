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
        override val description: DescriptionType,
        override val rules: List<GenericRule>,
        override val traits: Traits,
        override val publication: Publication,
        val acBonus: Int,
        override val baseItem: String?,
        val category: String,
        val checkPenalty: Int?,
        override val containerId: String?,
        val dexCap: Int,
        @Serializable(with = BsonValueSerializer::class) override val equippedBulk: BsonValue,
        val group: String?,
        override val hardness: Int,
        override val hp: HP,
        @Serializable(with = BsonValueSerializer::class) override val level: BsonValue,
        override val material: Material?,
        @Serializable(with = BsonValueSerializer::class) override val negateBulk: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val potencyRune: BsonValue?,
        override val price: Price,
        @Serializable(with = BsonValueSerializer::class) val propertyRune1: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val propertyRune2: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val propertyRune3: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val propertyRune4: BsonValue?,
        override val quantity: Int,
        @Serializable(with = BsonValueSerializer::class) val resiliencyRune: BsonValue?,
        override val size: String,
        val speedPenalty: Int?,
        override val stackGroup: String?,
        val strength: Int?,
        @Serializable(with = BsonValueSerializer::class) override val usage: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val weight: BsonValue,
    ) : EquipmentSystemModel

    override fun getSerializer(): KSerializer<*> = serializer()
}
