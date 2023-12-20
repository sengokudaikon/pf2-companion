package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Frequency
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.ActionTypes
import io.sengokudaikon.isn.infrastructure.domain.FeatureModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class FeatureResponse<T : FeatureModel>(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val description: DescriptionType,
    val publication: Publication,
    val traits: TraitsResponse,
    val rules: JsonElement?,
    val frequency: Frequency?,
    val isDefault: String?,
    val actionType: ActionTypes,
    val actions: JsonElement?,
    val category: String,
    val level: Int?,
    val maxTakable: String?,
    val prerequisites: JsonElement?,
    val trigger: String?,
    val effects: Response<FeatEffectModel>?
) : Response<T>()
