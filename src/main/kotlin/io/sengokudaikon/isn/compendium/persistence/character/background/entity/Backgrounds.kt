package io.sengokudaikon.isn.compendium.persistence.character.background.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.persistence.items.entity.Spells
import io.sengokudaikon.isn.compendium.persistence.world.entity.Actions
import io.sengokudaikon.isn.compendium.persistence.world.entity.Feats
import io.sengokudaikon.isn.compendium.persistence.world.entity.Traits
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Backgrounds : KotlinxUUIDTable("backgrounds") {
    val name = varchar("name", length = 50)
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", length = 10)
    val description = text("description")
    val code = varchar("code", length = 10)
    val isArchived = bool("is_archived").default(false)
    val rules = jsonb("rules", GenericRule.serializer())
    val contentSrc = varchar("content_src", length = 100)
    val trainedLore = varchar("trained_lore", length = 50).nullable().default(null)
}

object BackgroundItems : KotlinxUUIDTable("background_items") {
    val background = reference("background_id", Backgrounds)
    val feat = reference("feat", Feats).nullable().default(null)
    val action = reference("action", Actions).nullable().default(null)
    val spell = reference("spell", Spells).nullable().default(null)
}

object BackgroundBoosts : KotlinxUUIDTable("background_boosts") {
    val backgroundId = reference("background_id", Backgrounds)
    val boostedAbility = enumerationByName<io.sengokudaikon.isn.compendium.enums.Ability>(
        "boosted_ability",
        length = 20,
    )
}

object BackgroundSkills : KotlinxUUIDTable("background_skills") {
    val background = reference("background_id", Backgrounds)
    val skill = enumerationByName<Skills>("skill", 50)
    val proficiency =
        enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("skillProficiency", 50).default(
            io.sengokudaikon.isn.compendium.enums.Proficiency.UNTRAINED,
        )
}

object BackgroundTraits : KotlinxUUIDTable("background_trait") {
    val background = reference("background_id", Backgrounds)
    val trait = reference("trait_id", Traits)
}
