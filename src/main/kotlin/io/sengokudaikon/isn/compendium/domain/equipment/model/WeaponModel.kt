package io.sengokudaikon.isn.compendium.domain.equipment.model

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.EquipmentSystemModel
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.Traits
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
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
    @Serializable
    data class WeaponSystemProperty(
        override val description: DescriptionType,
        override val rules: List<GenericRule>,
        override val traits: Traits,
        override val publication: Publication,
        override val baseItem: String?,
        val bonus: JsonObject,
        val bonusDamage: JsonObject,
        val category: String,
        override val containerId: String?,
        val damage: Damage,
        override val equippedBulk: JsonObject,
        val group: String,
        override val hardness: Int,
        override val hp: HP,
        override val level: JsonObject,
        override val material: Material?,
        override val negateBulk: JsonObject,
        val potencyRune: JsonObject,
        override val price: Price,
        val propertyRune1: JsonObject?,
        val propertyRune2: JsonObject?,
        val propertyRune3: JsonObject?,
        val propertyRune4: JsonObject?,
        override val quantity: Int,
        val range: String?,
        val reload: JsonObject,
        override val size: String,
        val specific: Specific?,
        val splashDamage: JsonObject?,
        override val stackGroup: String?,
        val strikingRune: JsonObject,
        override val usage: JsonObject,
        override val weight: JsonObject,
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
