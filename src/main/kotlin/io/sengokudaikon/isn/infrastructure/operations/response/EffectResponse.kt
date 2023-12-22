package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.TokenIcon
import io.sengokudaikon.isn.infrastructure.domain.EffectModel
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
@ApiResponse(responseCode = "200", description = "Success")
data class EffectResponse<T : EffectModel>(
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
    val badge: JsonElement?,
    val duration: JsonElement?,
    val level: Int,
    val start: JsonElement?,
    val tokenIcon: TokenIcon
) : Response<T>()
