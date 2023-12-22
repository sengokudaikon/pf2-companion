package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.system.Frequency
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.infrastructure.domain.FeatureModel
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
@ApiResponse(responseCode = "200", description = "Success")
data class FeatureResponse<T : FeatureModel>(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val rarity: String?,
    val description: String,
    val publication: Publication,
    val traits: List<String>,
    val rules: JsonElement?,
    val frequency: Frequency?,
    val isDefault: String?,
    val actionType: JsonElement?,
    val actions: JsonElement?,
    val category: String?,
    val level: Int?,
    val maxTakable: String?,
    val prerequisites: JsonElement?,
    val trigger: String?,
    val effects: Response<FeatEffectModel>?,
) : Response<T>()
