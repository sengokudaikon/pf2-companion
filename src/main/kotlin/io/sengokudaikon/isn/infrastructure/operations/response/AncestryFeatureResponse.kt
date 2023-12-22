package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Frequency
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SelfEffect
import io.sengokudaikon.isn.compendium.enums.ActionTypes
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
@ApiResponse(responseCode = "200", description = "Success")
data class AncestryFeatureResponse(
    val id: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val description: DescriptionType,
    val publication: Publication,
    val traits: List<String>,
    val rules: JsonElement?,
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
) : Response<AncestryFeatureModel>()
