package io.sengokudaikon.isn.compendium.operations.global.response

import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Frequency
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.ActionTypes
import io.sengokudaikon.isn.infrastructure.domain.FeatureModel
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.Serializable

@Serializable
data class FeatureResponse<T : FeatureModel>(
    val id: String,
    val img: String,
    val name: String,
    val type: String,
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
    val effects: Response<FeatEffectModel>?
) : Response<T>
