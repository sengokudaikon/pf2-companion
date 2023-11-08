package io.sengokudaikon.dbfinder.persistence.character.background.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import io.sengokudaikon.dbfinder.persistence.items.entity.Spells
import io.sengokudaikon.dbfinder.persistence.world.entity.Actions
import io.sengokudaikon.dbfinder.persistence.world.entity.Feats
import io.sengokudaikon.dbfinder.persistence.world.entity.Traits
import io.sengokudaikon.fixtureloader.fixtures.model.system.GenericRule
import io.sengokudaikon.shared.persistence.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Backgrounds : KotlinxUUIDTable("backgrounds") {
    val name = varchar("name", length = 50)
    val rarity = enumerationByName<Rarity>("rarity", length = 10)
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
    val boostedAbility = enumerationByName<Ability>(
        "boosted_ability",
        length = 20,
    )
}

object BackgroundSkills : KotlinxUUIDTable("background_skills") {
    val background = reference("background_id", Backgrounds)
    val skill = enumerationByName<Skills>("skill", 50)
    val proficiency =
        enumerationByName<Proficiency>("skillProficiency", 50).default(
            Proficiency.UNTRAINED,
        )
}

object BackgroundTraits : KotlinxUUIDTable("background_trait") {
    val background = reference("background_id", Backgrounds)
    val trait = reference("trait_id", Traits)
}
