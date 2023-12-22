package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GeneralFeatResponse(
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
): Response<FeatModel>()