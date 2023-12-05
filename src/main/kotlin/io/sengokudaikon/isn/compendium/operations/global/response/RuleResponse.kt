package io.sengokudaikon.isn.compendium.operations.global.response

import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.operations.global.dto.DamageType
import io.sengokudaikon.isn.compendium.operations.global.vo.Choice
import io.sengokudaikon.isn.compendium.operations.global.vo.SubOption
import kotlinx.serialization.Serializable

@Serializable
data class RuleResponse(
    val domain: String? = null,
    val key: String? = null,
    val label: String? = null,
    val mode: String? = null,
    val type: String? = null,
    val option: String? = null,
    val suboptions: List<SubOption>? = emptyList(),
    val category: String? = null,
    val toggleable: Boolean? = null,
    val damage: DamageType? = null,
    val damageDice: String? = null,
    val damageType: String? = null,
    val overrides: List<String>? = emptyList(),
    val adjustment: Map<String, String>? = emptyMap(),
    val effects: List<GenericRule.RuleEffect>? = emptyList(),
    val range: String? = null,
    val predicate: String? = null,
    val hasHands: Boolean? = null,
    val selector: String? = null,
    val slug: String? = null,
    val choices: List<Choice> = listOf(),
    val flag: String? = null,
    val prompt: String? = null,
    val value: String? = null
)
