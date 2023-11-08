package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import io.sengokudaikon.dbfinder.infrastructure.enums.ArmourProficiencyIn
import io.sengokudaikon.dbfinder.infrastructure.enums.Proficiency
import io.sengokudaikon.dbfinder.infrastructure.enums.Rarity
import io.sengokudaikon.dbfinder.infrastructure.enums.Skills
import io.sengokudaikon.dbfinder.infrastructure.enums.WeaponClass
import io.sengokudaikon.dbfinder.persistence.items.entity.Weapons
import io.sengokudaikon.fixtureloader.fixtures.model.system.GenericRule
import io.sengokudaikon.fixtureloader.fixtures.model.system.ValueType
import io.sengokudaikon.shared.persistence.repository.jsonb
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Classes : KotlinxUUIDTable("char_classes") {
    val name = varchar("name", 50)
    val rarity = enumerationByName<Rarity>("rarity", length = 20)
    val description = text("description")
    val keyAttribute = enumerationByName<Ability>("key_attribute", length = 20)
    val hitPoints = integer("hit_points")
    val classDC = integer("class_dc")
    val rules = jsonb("rules", GenericRule.serializer())
    val skillIncreaseLevels = jsonb("skill_increase_levels", ValueType.IntListValue.serializer())
    val generalFeatLevels = jsonb("general_feat_levels", ValueType.IntListValue.serializer())
    val classFeatLevels = jsonb("class_feat_levels", ValueType.IntListValue.serializer())
    val skillFeatLevels = jsonb("skill_feat_levels", ValueType.IntListValue.serializer())
    val contentSrc = varchar("content_src", 100)
}

object ClassArmours : KotlinxUUIDTable("char_class_armour") {
    val classID = reference("class_id", Classes)
    val armour = enumerationByName<ArmourProficiencyIn>(
        "armour_class",
        length = 20,
    )
    val proficiency =
        enumerationByName<Proficiency>("armour_proficiency", 10)
}

object ClassSavingThrows : KotlinxUUIDTable("class_saving_throws") {
    val classID = reference("class_id", Classes)
    val savingThrow = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.SavingThrows>("saving_throw", 20)
    val proficiency = enumerationByName<Proficiency>("proficiency", 20)
}

object ClassSkills : KotlinxUUIDTable("char_class_skills") {
    val classID = reference("class_id", Classes)
    val skillID = enumerationByName<Skills>("skill_id", 10)
    val proficiency =
        enumerationByName<Proficiency>("proficiency", 20)
}

object ClassSpecificWeapons : KotlinxUUIDTable("char_class_specific_weapons") {
    val classID = reference("class_id", Classes)
    val weaponID = reference("weapon_id", Weapons)
    val proficiency = enumerationByName<Proficiency>("proficiency", 20)
}

object ClassWeapons : KotlinxUUIDTable("char_class_weapons") {
    val classID = reference("class_id", Classes)
    val weaponClass = enumerationByName<WeaponClass>(
        "weapon_class",
        length = 20,
    )
    val proficiency = enumerationByName<Proficiency>(
        "proficiency",
        length = 20,
    )
}
