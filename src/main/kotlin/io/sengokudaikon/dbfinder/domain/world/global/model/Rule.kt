package io.sengokudaikon.dbfinder.domain.world.global.model

import kotlinx.serialization.Serializable

@Serializable
data class Rule(
    val name: String,
    val description: String?,
    val mode: io.sengokudaikon.dbfinder.infrastructure.enums.RuleMode,
    val prompt: String,
    val ruleChoices: List<RuleChoice>,
)
