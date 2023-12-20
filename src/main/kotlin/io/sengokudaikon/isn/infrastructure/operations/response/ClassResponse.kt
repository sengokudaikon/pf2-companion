package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
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
    val description: DescriptionType,
    val publication: Publication,
    val rules: JsonElement?,
    val traits: TraitsResponse,
    val ancestryFeatLevels: String?,
    val attacks: ClassModel.Attacks,
    val defenses: ClassModel.Defenses,
    val hp: Int,
    val items: Map<String, ItemResponse>,
    val perception: Int,
    val savingThrows: ClassModel.SavingThrows,
    val trainedSkills: ClassModel.TrainedSkills,
    val skillFeatLevels: String?,
    val skillIncreaseLevels: String?,
    val classFeatLevels: String?,
    val generalFeatLevels: String?,
    val keyAbility: String?
) : Response<ClassModel>()
