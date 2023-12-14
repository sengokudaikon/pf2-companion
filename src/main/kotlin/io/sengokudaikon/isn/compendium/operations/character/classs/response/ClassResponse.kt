package io.sengokudaikon.isn.compendium.operations.character.classs.response

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.operations.global.response.ItemResponse
import io.sengokudaikon.isn.compendium.operations.global.response.RuleResponse
import io.sengokudaikon.isn.compendium.operations.global.response.TraitsResponse
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.Serializable

@Serializable
data class ClassResponse(
    val id: String,
    val img: String,
    val name: String,
    val type: String,
    val description: DescriptionType,
    val publication: Publication,
    val rules: List<RuleResponse>,
    val traits: TraitsResponse,
    val ancestryFeatLevels: String?,
    val attacks: ClassModel.Attacks,
    val classDC: Int,
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
) : Response<ClassModel>
