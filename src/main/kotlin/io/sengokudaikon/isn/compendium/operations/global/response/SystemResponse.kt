package io.sengokudaikon.isn.compendium.operations.global.response

import io.sengokudaikon.isn.compendium.domain.system.Languages
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.Ability
import kotlinx.serialization.Serializable

@Serializable
data class SystemResponse(
    val description: String,
    val rules: List<RuleResponse>,
    val traits: TraitsResponse,
    val publication: Publication,
    val additionalLanguages: Languages,
    val boosts: List<Ability>,
    val flaws: List<Ability>,
    val hp: Int,
    val languages: Languages,
    val reach: Int,
    val size: String,
    val source: String? = null,
    val speed: Int,
    val items: Map<String, ItemResponse>,
    val vision: String,
    val additionalSense: String?,
)
