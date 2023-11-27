package io.sengokudaikon.isn.compendium.domain.character.ancestry.model

import io.sengokudaikon.isn.compendium.domain.world.global.model.Language
import io.sengokudaikon.isn.compendium.domain.world.global.model.Trait
import io.sengokudaikon.isn.infrastructure.operations.Response
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
    val rules: String? = null,
    var abilityBoosts: List<AbilityBoost> = emptyList(),
    var abilityFlaws: List<AbilityFlaw> = emptyList(),
    var languages: List<Language> = emptyList(),
    var physicalFeatures: List<AncestryFeature> = emptyList(),
    var traits: List<Trait> = emptyList(),
) : Response
