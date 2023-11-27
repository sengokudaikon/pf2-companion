package io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.enums.Rarity
import io.sengokudaikon.isn.compendium.enums.Size
import io.sengokudaikon.isn.compendium.persistence.world.entity.Languages
import io.sengokudaikon.isn.compendium.persistence.world.entity.Traits
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Ancestries : KotlinxUUIDTable("char_ancestries") {
    val img = varchar("img", length = 100)
    val name = varchar("name", length = 50)
    val rarity = enumerationByName<Rarity>(length = 10, name = "rarity").default(Rarity.COMMON)
    val hitPoints = integer("hit_points")
    val size = enumerationByName<Size>(length = 10, name = "size").default(Size.MEDIUM)
    val speed = integer("speed")
    val description = text("description")
    val visionSenseID = reference("vision_sense_id", VisionSenses)
    val additionalSenseID = reference("additional_sense_id", VisionSenses).nullable()
    val rules = jsonb("rules", GenericRule.serializer())
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", length = 100)
}

object AncestryBoosts : KotlinxUUIDTable("char_ancestry_boosts") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val boostedAbility = enumerationByName<Ability>("boosted_ability", length = 20).default(Ability.Anything)
}

object AncestryFlaws : KotlinxUUIDTable("char_ancestry_flaws") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val flawedAbility = enumerationByName<Ability>("flawed_ability", length = 20).nullable().default(Ability.None)
}

object AncestryLanguages : KotlinxUUIDTable("char_ancestry_languages") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val languageID = reference("language_id", Languages)
    val isBonus = bool("is_bonus").default(false)
}

object AncestryTraits : KotlinxUUIDTable("char_ancestry_traits") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val trait = reference("trait_id", Traits)
}
