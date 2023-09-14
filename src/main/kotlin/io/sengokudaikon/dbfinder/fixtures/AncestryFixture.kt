package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AdditionalLanguages
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryDTO
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryPhysicalFeature
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.VisionSense
import io.sengokudaikon.dbfinder.domain.world.model.Language
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.operations.character.dto.Ability
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.Size

data class AncestryFixture(
    override val img: String,
    override val name: String,
    override val rarity: Rarity,
    override val additionalLanguages: AdditionalLanguages,
    override val boosts: List<Ability>,
    override val description: String,
    override val flaws: List<Ability>,
    override val hp: Int,
    override val languages: List<Language>,
    override val reach: Int,
    override val rules: List<AncestryFixtureLoader.JsonRules>,
    override val size: Size,
    override val source: String,
    override val speed: Int,
    override val traits: List<Trait>,
    override val vision: VisionSense,
    override val additionalSense: VisionSense?,
    override val physicalFeatures: List<AncestryPhysicalFeature>,
) : AncestryDTO
