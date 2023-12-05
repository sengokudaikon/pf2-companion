package io.sengokudaikon.isn.compendium.operations.global.dto

import kotlinx.serialization.Serializable

@Serializable
data class DamageType(
    val base: DamageTypeInner? = null
) {
    @Serializable
    data class DamageTypeInner(
        val damageType: String? = null,
        val dice: Int? = null,
        val die: String? = null,
    )
}
