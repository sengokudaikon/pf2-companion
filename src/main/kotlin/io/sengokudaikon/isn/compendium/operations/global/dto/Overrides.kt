package io.sengokudaikon.isn.compendium.operations.global.dto

import io.sengokudaikon.isn.compendium.operations.global.vo.Resistance
import io.sengokudaikon.isn.compendium.operations.global.vo.Weakness
import kotlinx.serialization.Serializable

@Serializable
data class Overrides(
    val armorClass: Map<String, String> = mapOf(),
    val resistances: List<Resistance> = listOf(),
    val senses: Map<String, String> = emptyMap(),
    val size: String? = null,
    val skills: Map<String, Map<String, Int>> = emptyMap(),
    val speeds: Map<String, Int> = emptyMap(),
    val strikes: Map<String, Strikes> = emptyMap(),
    val tempHP: Int? = null,
    val weaknesses: List<Weakness> = listOf(),
)
