package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
@ApiResponse(responseCode = "200", description = "Success")
data class HeritageResponse(
    val id: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val description: String,
    val rarity: String?,
    val publication: Publication,
    val traits: List<String>?,
    val rules: JsonElement?,
    val ancestryId: String,
): Response<HeritageModel>()
