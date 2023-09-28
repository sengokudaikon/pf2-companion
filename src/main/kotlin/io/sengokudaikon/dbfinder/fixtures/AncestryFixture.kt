package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AdditionalLanguages
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryPhysicalFeature
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.VisionSense
import io.sengokudaikon.dbfinder.domain.world.model.Language
import io.sengokudaikon.dbfinder.domain.world.model.Rule
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import kotlinx.serialization.Serializable

@Serializable
data class AncestryFixture(
    val img: String,
    val name: String,
    val rarity: io.sengokudaikon.dbfinder.infrastructure.enums.Rarity,
    val additionalLanguages: AdditionalLanguages,
    val boosts: List<io.sengokudaikon.dbfinder.infrastructure.enums.Ability>,
    val description: String,
    val flaws: List<io.sengokudaikon.dbfinder.infrastructure.enums.Ability>,
    val hp: Int,
    val languages: List<Language>,
    val reach: Int,
    val rules: List<Rule>,
    val size: io.sengokudaikon.dbfinder.infrastructure.enums.Size,
    val source: String,
    val speed: Int,
    val traits: List<Trait>,
    val vision: VisionSense,
    val additionalSense: VisionSense?,
    val physicalFeatures: List<AncestryPhysicalFeature>,
)
