package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class BestiaryResponse(
    val id: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val description: String,
    val rarity: String?,
    val publication: Publication,
    val traits: List<String>?,
    val rules: JsonElement?,
): Response<BestiaryModel>()