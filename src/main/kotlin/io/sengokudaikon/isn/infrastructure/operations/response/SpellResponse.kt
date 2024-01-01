package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SpellResponse(
    val id: String,
    val name: String,
    val img: String,
    @SerialName("type_")
    val type: String,
    val description: String,
    val rarity: String?,
    val publication: Publication,
    val traits: List<String>?,
    val rules: JsonElement?,
    val area: JsonElement?,
    val cost: JsonElement?,
    val counteraction: Boolean,
    val damage: JsonElement?,
    val defense: JsonElement?,
    val duration: JsonElement?,
    val level: Int?,
    val range: JsonElement?,
    val requirements: String,
    val target: JsonElement?,
    val time: JsonElement?,
    val heightening: JsonElement?,
    val overlays: JsonElement?,
): Response<SpellModel>()
