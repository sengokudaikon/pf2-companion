package io.sengokudaikon.dbfinder.domain.character.ancestry.model

import io.sengokudaikon.dbfinder.domain.world.model.Language
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.fixtures.AncestryFixtureLoader
import io.sengokudaikon.dbfinder.operations.character.dto.Ability
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.Size

interface AncestryDTO {
    val img: String
    val name: String
    val rarity: Rarity
    val additionalLanguages: AdditionalLanguages
    val boosts: List<Ability>
    val description: String
    val flaws: List<Ability>
    val hp: Int
    val languages: List<Language>
    val reach: Int
    val rules: List<AncestryFixtureLoader.JsonRules>
    val size: Size
    val source: String
    val speed: Int
    val traits: List<Trait>
    val vision: VisionSense
    val additionalSense: VisionSense?
    val physicalFeatures: List<AncestryPhysicalFeature>
}
