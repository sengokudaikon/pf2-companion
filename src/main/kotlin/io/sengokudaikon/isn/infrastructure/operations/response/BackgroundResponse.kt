package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.enums.Skills
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BackgroundResponse(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val feats: Map<String, Response<FeatModel>> = mapOf(),
    val actions: Map<String, Response<ActionModel>> = mapOf(),
    val description: String,
    val rules: String?,
    val publication: Publication,
    val traits: TraitsResponse,
    val boosts: List<Ability>,
    val trainedLore: String,
    val trainedSkills: List<Skills>
) : Response<BackgroundModel>()
