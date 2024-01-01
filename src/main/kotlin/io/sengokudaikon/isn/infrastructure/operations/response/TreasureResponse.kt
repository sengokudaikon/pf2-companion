package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.equipment.model.TreasureModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class TreasureResponse(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val baseItem: String?,
    val bulk: String?,
    val containerId: String?,
    val description: String,
    val hardness: Int,
    val hp: HP,
    val level: Int,
    val material: Material?,
    val price: Price,
    val publication: Publication,
    val quantity: Int,
    val rules: JsonElement?,
    val rarity: String?,
    val size: String,
    val traits: List<String>?,
): Response<TreasureModel>()
