package io.sengokudaikon.dbfinder.domain.character.ancestry.model

import io.sengokudaikon.dbfinder.domain.world.model.Language
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.shared.operations.Response
import kotlinx.serialization.Serializable

@Serializable
data class Ancestry(
    val id: String,
    val name: String,
    val description: String,
    val rarity: String,
    val hp: Int,
    val size: String,
    val speed: Int,
    var visionSense: VisionSense? = null,
    var additionalSense: VisionSense? = null,
    val artworkURL: String,
    val isArchived: Boolean,
    val contentSrc: String,
    val homebrewID: Int?,
    val version: String,
    var abilityBoosts: List<AbilityBoost> = emptyList(),
    var abilityFlaws: List<AbilityFlaw> = emptyList(),
    var languages: List<Language> = emptyList(),
    var physicalFeatures: List<AncestryPhysicalFeature> = emptyList(),
    var traits: List<Trait> = emptyList(),
    var rules: List<AncestryRule> = emptyList(),
) : Response
