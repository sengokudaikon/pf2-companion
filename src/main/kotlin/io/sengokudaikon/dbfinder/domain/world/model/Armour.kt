package io.sengokudaikon.dbfinder.domain.world.model

import kotlinx.serialization.Serializable

@Serializable
data class Armour(
    val name: String,
    val itemId: String,
    val proficiency: io.sengokudaikon.dbfinder.infrastructure.enums.ArmourProficiencyIn,
    val acBonus: Int,
    val dexCap: Int,
    val strengthRequired: Int,
    val checkPenalty: Int,
    val speedPenalty: Int,
    val armourType: io.sengokudaikon.dbfinder.infrastructure.enums.ArmourType,
)
