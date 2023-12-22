package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.Traits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ArmorResponse(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val rarity: String?,
    val group: String,
    val resiliencyRune: JsonElement?,
    val specific: ArmorModel.SystemProperty.Specific?,
    val speedPenalty: Int?,
    val acBonus: Int,
    val dexCap: Int,
    val category: String,
    val checkPenalty: Int,
    val strength: Int,
    val potency: JsonElement?,
    val potencyRune: JsonElement?,
    val propertyRune1: JsonElement?,
    val propertyRune2: JsonElement?,
    val propertyRune3: JsonElement?,
    val propertyRune4: JsonElement?,
    val description: String,
    val rules: JsonElement,
    val traits: Traits,
    val publication: Publication,
    val baseItem: String?,
    val containerId: String?,
    val hardness: Int,
    val hp: HP,
    val material: Material?,
    val price: Price,
    val quantity: Int,
    val size: String,
    val stackGroup: String?,
    val equippedBulk: JsonElement?,
    val negateBulk: JsonElement?,
    val level: JsonElement?,
    val usage: JsonElement?,
    val weight: JsonElement?
) : Response<ArmorModel>()
