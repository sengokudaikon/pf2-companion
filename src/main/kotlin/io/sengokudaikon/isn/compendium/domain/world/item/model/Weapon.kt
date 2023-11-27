package io.sengokudaikon.isn.compendium.domain.world.item.model

import io.sengokudaikon.isn.compendium.enums.WeaponClass
import io.sengokudaikon.isn.compendium.enums.WeaponType
import kotlinx.serialization.Serializable

@Serializable
data class Weapon(
    val name: String,
    val itemId: String,
    val damageType: io.sengokudaikon.isn.compendium.enums.DamageType,
    val damageDice: io.sengokudaikon.isn.compendium.enums.DamageDice,
    val weaponClass: WeaponClass,
    val weaponType: WeaponType,
    val range: Int,
    val reload: Int? = 0,
)
