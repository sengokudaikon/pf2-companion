package io.sengokudaikon.dbfinder.domain.world.model

import io.sengokudaikon.dbfinder.infrastructure.enums.WeaponClass
import io.sengokudaikon.dbfinder.infrastructure.enums.WeaponType
import kotlinx.serialization.Serializable

@Serializable
data class Weapon(
    val name: String,
    val itemId: String,
    val damageType: io.sengokudaikon.dbfinder.infrastructure.enums.DamageType,
    val damageDice: io.sengokudaikon.dbfinder.infrastructure.enums.DamageDice,
    val weaponClass: WeaponClass,
    val weaponType: WeaponType,
    val range: Int,
    val reload: Int? = 0,
)
