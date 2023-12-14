package io.sengokudaikon.isn.compendium.operations.character.equipment.response

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.compendium.operations.global.response.RuleResponse
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.Serializable

@Serializable
data class ArmorResponse(
    val id: String,
    val img: String,
    val name: String,
    val type: String,
    val group: String,
    val resiliencyRune: String?,
    val specific: ArmorModel.SystemProperty.Specific?,
    val speedPenalty: Int?,
    val acBonus: Int,
    val dexCap: Int,
    val category: String,
    val checkPenalty: Int,
    val strength: Int,
    val potency: String?,
    val potencyRune: String,
    val propertyRune1: String,
    val propertyRune2: String,
    val propertyRune3: String,
    val propertyRune4: String,
    val description: DescriptionType,
    val rules: List<RuleResponse>,
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
    val equippedBulk: String,
    val negateBulk: String,
    val level: String,
    val usage: String,
    val weight: String
) : Response<ArmorModel>
