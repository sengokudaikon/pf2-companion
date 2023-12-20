package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ShieldResponse(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val acBonus: Int,
    val baseItem: String?,
    val bulk: ShieldModel.SystemProperty.Bulk,
    val containerId: String?,
    val description: DescriptionType,
    val hardness: Int,
    val hp: HP,
    val level: Int,
    val material: Material?,
    val price: Price,
    val publication: Publication,
    val quantity: Int,
    val rules: JsonElement,
    val size: String,
    val specific: ShieldModel.SystemProperty.Specific?,
    val speedPenalty: Int,
    val stackGroup: String?,
    val traits: TraitsResponse,
    val usage: JsonElement?
) : Response<ShieldModel>()
