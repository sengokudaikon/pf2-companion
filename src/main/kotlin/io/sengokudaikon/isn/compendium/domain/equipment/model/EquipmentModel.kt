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
data class EquipmentModel(
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
        override val baseItem: String?,
        override val containerId: String?,
        override val equippedBulk: JsonObject,
        override val hardness: Int,
        override val hp: HP,
        override val level: JsonObject,
        override val material: Material?,
        override val negateBulk: JsonObject,
        override val price: Price,
        override val quantity: Int,
        override val size: String,
        override val stackGroup: String?,
        override val usage: JsonObject,
        override val weight: JsonObject,
    ) : EquipmentSystemModel

    override fun getSerializer(): KSerializer<*> = serializer()
}
