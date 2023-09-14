package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.Ancestries
import io.sengokudaikon.dbfinder.persistence.character.background.entity.Backgrounds
import io.sengokudaikon.dbfinder.persistence.character.background.entity.Heritages
import io.sengokudaikon.dbfinder.persistence.character.classs.entity.Classes
import io.sengokudaikon.kfinder.persistence.user.entity.Users
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Characters : KotlinxUUIDTable("characters") {
    val userID = reference("user_id", Users)
    val buildId = integer("build_id")
    val name = varchar("name", length = 50)
    val level = integer("level")
    val experience = integer("experience")
    val ancestryID = reference("ancestry_id", Ancestries)
    val backgroundID = reference("background_id", Backgrounds)
    val heritageID = reference("heritage_id", Heritages)
    val classID = reference("class_id", Classes)
    val classID2 = reference("class_id2", Classes).nullable()
    val health = integer("health")
    val currentHealth = integer("current_health")
    val tempHealth = integer("temp_health")
    val heroPoints = integer("hero_points")
    val notes = text("notes")
    val info = text("info")
    val details = text("details")
    val portraitUrl = text("portrait_url")
    val customCode = text("custom_code")
    val dataID = integer("data_id")
    val currentStamina = integer("current_stamina")
    val stamina = integer("stamina")
    val currentResolve = integer("current_resolve")
    val resolve = integer("resolve")
    val optionDualClass = bool("option_dual_class").default(false)
    val optionProficiencyWithoutLevel = bool("option_proficiency_without_level").default(false)
    val optionAutoBonusProgression = bool("option_auto_bonus").default(false)
    val optionFreeArchetype = bool("option_free_archetype").default(false)
    val optionStamina = bool("option_stamina").default(false)
    val optionResolve = bool("option_resolve").default(false)
    val optionAncestryParagon = bool("option_ancestry_paragon").default(false)
    val optionGradualAbilityBoosts = bool("option_gradual_ability_boosts").default(false)
}
