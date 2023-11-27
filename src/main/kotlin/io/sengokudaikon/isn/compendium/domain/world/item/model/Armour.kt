package io.sengokudaikon.isn.compendium.domain.world.item.model

import kotlinx.serialization.Serializable

@Serializable
data class Armour(
    val name: String,
    val itemId: String,
    val proficiency: io.sengokudaikon.isn.compendium.enums.ArmourProficiencyIn,
    val acBonus: Int,
    val dexCap: Int,
    val strengthRequired: Int,
    val checkPenalty: Int,
    val speedPenalty: Int,
    val armourType: io.sengokudaikon.isn.compendium.enums.ArmourType,
)
