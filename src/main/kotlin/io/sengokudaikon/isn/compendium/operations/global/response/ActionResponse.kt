package io.sengokudaikon.isn.compendium.operations.global.response

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Frequency
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SelfEffect
import io.sengokudaikon.isn.compendium.enums.ActionTypes
import kotlinx.serialization.Serializable

@Serializable
data class ActionResponse(
    val description: DescriptionType,
    val publication: Publication,
    val traits: TraitsResponse,
    val rules: List<RuleResponse>,
    val frequency: Frequency?,
    val isDefault: String?,
    val actionType: ActionTypes,
    val actions: String?,
    val category: String,
    val level: Int?,
    val maxTakable: String?,
    val prerequisites: String?,
    val trigger: String?,
    val selfEffect: SelfEffect?
)
