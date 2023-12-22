package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ClassResponse(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val rarity: String?,
    val description: String,
    val publication: Publication,
    val rules: JsonElement?,
    val traits: List<String>,
    val ancestryFeatLevels: JsonElement?,
    val attacks: ClassModel.Attacks,
    val defenses: ClassModel.Defenses,
    val hp: Int,
    val perception: Int,
    val savingThrows: ClassModel.SavingThrows,
    val features: List<ClassFeatureResponse>,
    val trainedSkills: ClassModel.TrainedSkills,
    val skillFeatLevels: JsonElement?,
    val skillIncreaseLevels: JsonElement?,
    val classFeatLevels: JsonElement?,
    val generalFeatLevels: JsonElement?,
    val keyAbility: JsonElement?
) : Response<ClassModel>()
