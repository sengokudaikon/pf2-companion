package io.sengokudaikon.dbfinder.domain.world.model

import kotlinx.serialization.Serializable

@Serializable
data class Rule(
    val name: String,
    val mode: io.sengokudaikon.dbfinder.infrastructure.enums.RuleMode,
    val priority: Int,
    val prompt: String,
    val ruleChoices: List<RuleChoice>,
    val contentSrc: String,
    val description: String? = null,
    val isArchived: Boolean = false,
)
