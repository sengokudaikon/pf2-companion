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
        override val rules: List<GenericRule>,
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
        val range: String?,
        override val size: String,
        val specific: Specific?,
        override val stackGroup: String?,
        @Serializable(with = BsonValueSerializer::class) val bonus: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val bonusDamage: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val equippedBulk: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val level: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val negateBulk: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val potencyRune: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val propertyRune1: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val propertyRune2: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val propertyRune3: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val propertyRune4: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val reload: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val splashDamage: BsonValue?,
        @Serializable(with = BsonValueSerializer::class) val strikingRune: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val usage: BsonValue,
        @Serializable(with = BsonValueSerializer::class) override val weight: BsonValue,
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
            val value: Boolean,
        ) {
            @Serializable
            data class Runes(
                val potency: Int,
                val striking: String,
            )
        }
    }

    override fun getSerializer(): KSerializer<*> = serializer()
}
