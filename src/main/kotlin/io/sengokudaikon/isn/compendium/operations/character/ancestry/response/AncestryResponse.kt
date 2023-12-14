package io.sengokudaikon.isn.compendium.operations.character.ancestry.response

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.system.Languages
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.operations.global.response.ItemResponse
import io.sengokudaikon.isn.compendium.operations.global.response.RuleResponse
import io.sengokudaikon.isn.compendium.operations.global.response.TraitsResponse
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.Serializable

@Serializable
data class AncestryResponse(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    val rules: List<RuleResponse>,
    val traits: TraitsResponse,
    val publication: Publication,
    val items: Map<String, ItemResponse>,
    val boosts: List<Ability>,
    val additionalLanguages: Languages,
    val flaws: List<Ability>,
    val hp: Int,
    val languages: Languages,
    val reach: Int,
    val size: String,
    val source: String? = null,
    val speed: Int,
    val vision: String,
    val additionalSense: String?,
    val ancestryFeatures: Map<String, Response<AncestryFeatureModel>>,
) : Response<AncestryModel>
