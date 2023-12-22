package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.Skills
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
@ApiResponse(responseCode = "200", description = "Success")
data class BackgroundResponse(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val rarity: String?,
    val feats: Map<String, Response<FeatModel>> = mapOf(),
    val actions: Map<String, Response<ActionModel>> = mapOf(),
    val description: String,
    val rules: JsonElement?,
    val publication: Publication,
    val traits: List<String>,
    val boosts: String,
    val trainedLore: String,
    val trainedSkills: List<Skills>
) : Response<BackgroundModel>()
