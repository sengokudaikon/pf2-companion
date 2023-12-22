package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ClassFeatureResponse(
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
    val actionType: JsonElement?,
    val actions: JsonElement?,
    val category: String?,
    val level: Int?,
    val prerequisites: JsonElement?,
) : Response<ClassFeatureModel>()
