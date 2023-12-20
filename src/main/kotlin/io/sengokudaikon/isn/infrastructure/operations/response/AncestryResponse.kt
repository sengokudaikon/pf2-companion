package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.system.Languages
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.Ability
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class AncestryResponse(
    val id: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val description: String,
    val rules: JsonElement?,
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
) : Response<AncestryModel>()
