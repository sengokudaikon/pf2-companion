package io.sengokudaikon.isn.compendium.domain.world.global.model

import kotlinx.serialization.Serializable

@Serializable
data class Rule(
    val name: String,
    val description: String?,
    val mode: io.sengokudaikon.isn.compendium.enums.RuleMode,
    val prompt: String,
    val ruleChoices: List<RuleChoice>,
)
