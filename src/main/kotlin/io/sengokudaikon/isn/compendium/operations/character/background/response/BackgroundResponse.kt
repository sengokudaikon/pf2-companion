package io.sengokudaikon.isn.compendium.operations.character.background.response

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.operations.global.response.ItemResponse
import io.sengokudaikon.isn.compendium.operations.global.response.RuleResponse
import io.sengokudaikon.isn.compendium.operations.global.response.TraitsResponse
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.Serializable

@Serializable
data class BackgroundResponse(
    val id: String,
    val img: String,
    val name: String,
    val type: String,
    val feats: Map<String, Response<FeatModel>>,
    val actions: Map<String, Response<ActionModel>>,
    val description: String,
    val rules: List<RuleResponse>,
    val publication: Publication,
    val traits: TraitsResponse,
    val boosts: List<Ability>,
    val items: Map<String, ItemResponse>,
    val trainedLore: String,
    val trainedSkills: List<Skills>
) : Response<BackgroundModel>
