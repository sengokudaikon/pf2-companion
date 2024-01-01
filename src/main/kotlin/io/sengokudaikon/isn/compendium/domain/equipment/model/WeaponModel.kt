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
data class WeaponModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: WeaponSystemProperty,
) : Model {
    @OptIn(ExperimentalSerializationApi::class)
    @Serializable
    data class WeaponSystemProperty(
        override val description: DescriptionType,
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        override val traits: Traits,
        override val publication: Publication,
        override val baseItem: String?,
        val category: String,
        override val containerId: String?,
        val damage: Damage,
        val group: String,
        override val hardness: Int,
        override val hp: HP,
        override val material: Material?,
        override val price: Price,
        override val quantity: Int,
        val range: Int?,
        override val size: String,
        val specific: Specific?,
        @Serializable(with = BsonValueSerializer::class) val bonus: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val bonusDamage: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val level: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val reload: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val splashDamage: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) override val usage: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) override val bulk: BsonValue,
    ) : EquipmentSystemModel {
        @Serializable
        data class Damage(
            val damageType: String,
            val dice: Int,
            val die: String,
        )

        @Serializable
        data class Specific(
            val material: Material?,
            val runes: Runes?,
            val value: Boolean? = null,
        ) {
            @Serializable
            data class Runes(
                val potency: Int,
                val striking: Int,
                val property: List<String>,
            )
        }
    }
}
