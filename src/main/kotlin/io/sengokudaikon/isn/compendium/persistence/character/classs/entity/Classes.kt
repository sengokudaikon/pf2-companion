package io.sengokudaikon.isn.compendium.persistence.character.classs.entity

import io.sengokudaikon.isn.builder.fixtures.model.system.GenericRule
import io.sengokudaikon.isn.builder.fixtures.model.system.Value
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.enums.WeaponClass
import io.sengokudaikon.isn.compendium.persistence.items.entity.Weapons
import io.sengokudaikon.isn.infrastructure.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Classes : KotlinxUUIDTable("char_classes") {
    val name = varchar("name", 50)
    val rarity = enumerationByName<io.sengokudaikon.isn.compendium.enums.Rarity>("rarity", length = 20)
    val description = text("description")
    val keyAttribute = enumerationByName<io.sengokudaikon.isn.compendium.enums.Ability>("key_attribute", length = 20)
    val hitPoints = integer("hit_points")
    val classDC = integer("class_dc")
    val rules = jsonb("rules", GenericRule.serializer())
    val skillIncreaseLevels = jsonb("skill_increase_levels", Value.IntList.serializer())
    val generalFeatLevels = jsonb("general_feat_levels", Value.IntList.serializer())
    val classFeatLevels = jsonb("class_feat_levels", Value.IntList.serializer())
    val skillFeatLevels = jsonb("skill_feat_levels", Value.IntList.serializer())
    val contentSrc = varchar("content_src", 100)
}

object ClassArmours : KotlinxUUIDTable("char_class_armour") {
    val classID = reference("class_id", Classes)
    val armour = enumerationByName<io.sengokudaikon.isn.compendium.enums.ArmourProficiencyIn>(
        "armour_class",
        length = 20,
    )
    val proficiency =
        enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("armour_proficiency", 10)
}

object ClassSavingThrows : KotlinxUUIDTable("class_saving_throws") {
    val classID = reference("class_id", Classes)
    val savingThrow = enumerationByName<io.sengokudaikon.isn.compendium.enums.SavingThrows>("saving_throw", 20)
    val proficiency = enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("proficiency", 20)
}

object ClassSkills : KotlinxUUIDTable("char_class_skills") {
    val classID = reference("class_id", Classes)
    val skillID = enumerationByName<Skills>("skill_id", 10)
    val proficiency =
        enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("proficiency", 20)
}

object ClassSpecificWeapons : KotlinxUUIDTable("char_class_specific_weapons") {
    val classID = reference("class_id", Classes)
    val weaponID = reference("weapon_id", Weapons)
    val proficiency = enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>("proficiency", 20)
}

object ClassWeapons : KotlinxUUIDTable("char_class_weapons") {
    val classID = reference("class_id", Classes)
    val weaponClass = enumerationByName<WeaponClass>(
        "weapon_class",
        length = 20,
    )
    val proficiency = enumerationByName<io.sengokudaikon.isn.compendium.enums.Proficiency>(
        "proficiency",
        length = 20,
    )
}
