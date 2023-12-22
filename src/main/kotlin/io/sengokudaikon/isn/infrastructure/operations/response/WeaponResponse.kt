package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.Traits
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class WeaponResponse(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val group: String,
    val specific: WeaponModel.WeaponSystemProperty.Specific?,
    val potencyRune: String,
    val propertyRune1: String,
    val propertyRune2: String,
    val propertyRune3: String,
    val propertyRune4: String,
    val description: String,
    val rules: JsonElement?,
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
    val weight: String,
    val reload: String,
    val bonus: String,
    val strikingRune: String,
    val damage: WeaponModel.WeaponSystemProperty.Damage,
    val bonusDamage: String
) : Response<WeaponModel>()
