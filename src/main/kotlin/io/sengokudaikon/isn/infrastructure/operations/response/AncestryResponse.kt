package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.domain.system.Languages
import io.sengokudaikon.isn.compendium.domain.system.Publication
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class AncestryResponse(
    val id: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val rarity: String?,
    val description: String,
    val rules: JsonElement?,
    val traits: List<String>,
    val publication: Publication,
    val boosts: String,
    val additionalLanguages: Languages,
    val flaws: String,
    val hp: Int,
    val languages: Languages,
    val reach: Int,
    val size: String,
    val source: String? = null,
    val speed: Int,
    val vision: String,
    val additionalSense: String?,
    val ancestryFeatures: Map<String, Response<AncestryFeatureModel>>,
    val heritages: Map<String, Response<HeritageModel>>,
) : Response<AncestryModel>()
