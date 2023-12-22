package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.operations.global.dto.DamageType
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
@ApiResponse(responseCode = "200", description = "Success")
data class RuleResponse(
    val domain: String? = null,
    val key: String? = null,
    val label: String? = null,
    val mode: String? = null,
    val type: String? = null,
    val option: String? = null,
    val suboptions: JsonElement? = null,
    val category: String? = null,
    val toggleable: Boolean? = null,
    val damage: DamageType? = null,
    val damageDice: String? = null,
    val damageType: String? = null,
    val overrides: JsonElement? = null,
    val adjustment: JsonElement? = null,
    val effects: JsonElement? = null,
    val range: String? = null,
    val predicate: JsonElement? = null,
    val hasHands: Boolean? = null,
    val selector: String? = null,
    val slug: String? = null,
    val choices: JsonElement? = null,
    val flag: String? = null,
    val prompt: String? = null,
    val value: JsonElement? = null
) {
    @Serializable
    data class ChoiceResponse(
        val label: String? = null,
        val value: String? = null
    )
}
