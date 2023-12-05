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
data class ShieldModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
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
        override val equippedBulk: JsonObject,
        val group: String?,
        override val hardness: Int,
        override val hp: HP,
        override val level: JsonObject,
        override val material: Material?,
        override val negateBulk: JsonObject,
        val potencyRune: JsonObject?,
        override val price: Price,
        val propertyRune1: JsonObject?,
        val propertyRune2: JsonObject?,
        val propertyRune3: JsonObject?,
        val propertyRune4: JsonObject?,
        override val quantity: Int,
        val resiliencyRune: JsonObject?,
        override val size: String,
        val speedPenalty: Int?,
        override val stackGroup: String?,
        val strength: Int?,
        override val usage: JsonObject,
        override val weight: JsonObject,
    ) : EquipmentSystemModel

    override fun getSerializer(): KSerializer<*> = serializer()
}
