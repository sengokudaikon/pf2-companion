package io.sengokudaikon.isn.chargen.infrastructure

import io.sengokudaikon.isn.chargen.domain.model.AnimalCompanionModel
import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.domain.model.DedicationModel
import io.sengokudaikon.isn.chargen.domain.model.FamiliarModel
import io.sengokudaikon.isn.chargen.domain.model.PathbuilderCharacter
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.domain.background.repository.BackgroundRepositoryPort
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.compendium.domain.equipment.repository.WeaponRepositoryPort
import io.sengokudaikon.isn.compendium.domain.familiar.repository.FamiliarAbilityRepositoryPort
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.feat.repository.FeatRepositoryPort
import io.sengokudaikon.isn.compendium.domain.heritage.repository.HeritageRepositoryPort
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.enums.BoundedInt
import io.sengokudaikon.isn.compendium.enums.DamageType
import io.sengokudaikon.isn.compendium.enums.Proficiency
import io.sengokudaikon.isn.compendium.enums.SaveType
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.compendium.enums.WeaponClass
import org.bson.types.ObjectId

class PathbuilderConverter(
    private val ancestryRepository: AncestryRepositoryPort,
    private val ancestryFeatRepository: AncestryFeatureRepositoryPort,
    private val backgroundRepository: BackgroundRepositoryPort,
    private val classRepository: ClassRepositoryPort,
    private val classFeatRepository: ClassFeatureRepositoryPort,
    private val featRepository: FeatRepositoryPort,
    private val heritageRepository: HeritageRepositoryPort,
    private val familiarAbilityRepository: FamiliarAbilityRepositoryPort,
    private val weaponRepository: WeaponRepositoryPort,
) {
    private fun fromAttributeClassDc(abilities: PathbuilderCharacter.Abilities, keyability: String): Int {
        return when (keyability) {
            "Strength" -> abilities.strength
            "Dexterity" -> abilities.dexterity
            "Constitution" -> abilities.constitution
            "Intelligence" -> abilities.intelligence
            "Wisdom" -> abilities.wisdom
            "Charisma" -> abilities.charisma
            else -> 0
        }
    }

    suspend fun convert(import: PathbuilderCharacter): CharacterModel {
        return CharacterModel(
            ObjectId(),
            import.name,
            "",
            "Character",
            "",
            CharacterModel.CharacterSystem(
                DescriptionType("Imported from Pathbuilder"),
                traits = null,
                level = import.level,
                ancestry = import.ancestry.let {
                    ancestryRepository.findByName(it).getOrThrow()
                },
                background = import.background.let {
                    backgroundRepository.findByName(it).getOrThrow()
                },
                baseClass = import.classs.let {
                    classRepository.findByName(it).getOrThrow()
                },
                heritage = import.heritage.let {
                    heritageRepository.findByName(it).getOrThrow()
                },
                ancestryFeats = getAncestryFeats(import.feats),
                attributes = getAttributes(import.abilities),
                attributesLeveledAt = mapOf(),
                classFeats = getClassFeats(import.feats),
                classDc = CharacterModel.ClassDC(
                    fromProficiency = import.proficiencies.classDC,
                    fromAttribute = fromAttributeClassDc(import.abilities, import.keyability),
                    fromItem = 0,
                    value = 0,
                    fromLevel = import.level
                ).apply { value = this.calculate() },
                currentExp = 0,
                dedications = getDedications(import.feats),
                dualClass = import.dualClass?.let {
                    classRepository.findByName(it).getOrThrow()
                },
                familiars = import.familiars.map {
                    FamiliarModel(
                        ObjectId(),
                        "",
                        name = it.name,
                        type = it.type,
                        system = FamiliarModel.FamiliarSystem(
                            description = DescriptionType("Imported from Pathbuilder"),
                            publication = Publication("ORC", true, "Inner Sea Navigator"),
                            traits = null,
                            rules = null,
                            equipment = it.equipment,
                            specific = it.specific,
                        )
                    ).apply {
                        this.abilities = it.abilities.map {
                            familiarAbilityRepository.findByName(it).getOrThrow()
                        }
                    }
                },
                formula = listOf(),
                heroPoints = 0,
                hp = CharacterModel.HP(
                    dying = BoundedInt(0),
                    wounded = BoundedInt(0),
                    max = import.attributes.classHp + import.attributes.ancestryHp + import.attributes.bonusHp +
                        import.level * import.attributes.bonusHpPerLevel,
                    current = import.attributes.classHp + import.attributes.ancestryHp + import.attributes.bonusHp +
                        import.level * import.attributes.bonusHpPerLevel,
                    temporary = 0
                ),
                languages = import.languages,
                perception = CharacterModel.Perception(
                    proficiency = Proficiency.valueOf(import.proficiencies.perception),
                    fromProficiency = import.proficiencies.perception,
                    fromAttribute = import.abilities.wisdom,
                    fromItem = import.mods["Perception"]?.get("Item Bonus") ?: 0,
                    fromLevel = import.level,
                    senses = import.specials.filter {
                        it.contains("Vision") or it.contains("Sense") or it.contains("vision") or it.contains(
                            "sense"
                        )
                    },
                ).apply { value = this.calculate() },
                pets = import.pets.map {
                    AnimalCompanionModel(
                        ObjectId(),
                        "",
                        name = it.name,
                        type = it.type,
                        system = AnimalCompanionModel.AnimalCompanionSystem(
                            description = DescriptionType("Imported from Pathbuilder"),
                            publication = Publication("ORC", true, "Inner Sea Navigator"),
                            traits = null,
                            rules = null,
                            equipment = it.equipment,
                            armor = it.armor,
                            specializations = it.specializations,
                            maturity = if (it.mature) "Mature" else if (it.incredible) "Incredible" else "Young",
                        )
                    )
                },
                saves = getSaves(import),
                skills = getSkills(import),
                dedicationFeats = getDedicationFeats(import.feats),
                generalFeats = getGeneralFeats(import.feats),
                skillFeats = getSkillFeats(import.feats),
                speed = CharacterModel.Speed(
                    import.attributes.speed,
                    import.attributes.speedBonus,
                    specialMovement = listOf()
                ),
                spellcasting = listOf(),
                strikes = getStrikes(import),
                weaponProficiencies = CharacterModel.WeaponProficiency(
                    simple = import.proficiencies.simple,
                    martial = import.proficiencies.martial,
                    advanced = import.proficiencies.advanced,
                    unarmed = import.proficiencies.unarmed,
                    other = Proficiency.UNTRAINED,
                    additionalWeapons = listOf(),
                    criticalSpecializations = listOf(),
                ),
            )
        )
    }

    private suspend fun getStrikes(import: PathbuilderCharacter): MutableList<CharacterModel.Strike> {
        val strikes = mutableListOf<CharacterModel.Strike>()
        import.weapons.forEach {
            strikes.add(
                CharacterModel.Strike(
                    weapon = it.name,
                    value = it.damageBonus.toInt(),
                    damage = it.die,
                    damageType = DamageType.valueOf(it.damageType),
                    fromAttribute = import.abilities.strength,
                    additionalDamage = it.extraDamage,
                    traits = listOf(it.runes.joinToString(","), it.mat, it.str),
                    fromItem = it.pot.toInt(),
                    fromProficiency = 0
                ).apply {
                    itemWeapon = weaponRepository.findByName(it.name).getOrThrow()
                    val prof = WeaponClass.valueOf(itemWeapon.system.group)
                    fromProficiency = when (prof) {
                        WeaponClass.SIMPLE -> import.proficiencies.simple
                        WeaponClass.MARTIAL -> import.proficiencies.martial
                        WeaponClass.ADVANCED -> import.proficiencies.advanced
                        WeaponClass.UNARMED -> import.proficiencies.unarmed
                    }
                }
            )
        }
        return strikes
    }

    @Suppress("CyclomaticComplexMethod")
    private fun getSkills(import: PathbuilderCharacter): List<CharacterModel.Skill> {
        val skills = mutableListOf(
            CharacterModel.Skill(
                name = Skills.Arcana.name,
                proficiency = Proficiency.valueOf(import.proficiencies.arcana),
                fromProficiency = import.proficiencies.arcana,
                fromAttribute = import.abilities.intelligence,
                fromItem = import.mods["Arcana"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Athletics.name,
                proficiency = Proficiency.valueOf(import.proficiencies.athletics),
                fromProficiency = import.proficiencies.athletics,
                fromAttribute = import.abilities.strength,
                fromItem = import.mods["Athletics"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Crafting.name,
                proficiency = Proficiency.valueOf(import.proficiencies.crafting),
                fromProficiency = import.proficiencies.crafting,
                fromAttribute = import.abilities.intelligence,
                fromItem = import.mods["Crafting"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Acrobatics.name,
                proficiency = Proficiency.valueOf(import.proficiencies.acrobatics),
                fromProficiency = import.proficiencies.acrobatics,
                fromAttribute = import.abilities.strength,
                fromItem = import.mods["Acrobatics"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Deception.name,
                proficiency = Proficiency.valueOf(import.proficiencies.deception),
                fromProficiency = import.proficiencies.deception,
                fromAttribute = import.abilities.charisma,
                fromItem = import.mods["Deception"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Diplomacy.name,
                proficiency = Proficiency.valueOf(import.proficiencies.diplomacy),
                fromProficiency = import.proficiencies.diplomacy,
                fromAttribute = import.abilities.charisma,
                fromItem = import.mods["Diplomacy"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Intimidation.name,
                proficiency = Proficiency.valueOf(import.proficiencies.intimidation),
                fromProficiency = import.proficiencies.intimidation,
                fromAttribute = import.abilities.charisma,
                fromItem = import.mods["Intimidation"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Medicine.name,
                proficiency = Proficiency.valueOf(import.proficiencies.medicine),
                fromProficiency = import.proficiencies.medicine,
                fromAttribute = import.abilities.wisdom,
                fromItem = import.mods["Medicine"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Nature.name,
                proficiency = Proficiency.valueOf(import.proficiencies.nature),
                fromProficiency = import.proficiencies.nature,
                fromAttribute = import.abilities.wisdom,
                fromItem = import.mods["Nature"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Occultism.name,
                proficiency = Proficiency.valueOf(import.proficiencies.occultism),
                fromProficiency = import.proficiencies.occultism,
                fromAttribute = import.abilities.intelligence,
                fromItem = import.mods["Occultism"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Performance.name,
                proficiency = Proficiency.valueOf(import.proficiencies.performance),
                fromProficiency = import.proficiencies.performance,
                fromAttribute = import.abilities.charisma,
                fromItem = import.mods["Performance"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Religion.name,
                proficiency = Proficiency.valueOf(import.proficiencies.religion),
                fromProficiency = import.proficiencies.religion,
                fromAttribute = import.abilities.wisdom,
                fromItem = import.mods["Religion"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Society.name,
                proficiency = Proficiency.valueOf(import.proficiencies.society),
                fromProficiency = import.proficiencies.society,
                fromAttribute = import.abilities.intelligence,
                fromItem = import.mods["Society"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Stealth.name,
                proficiency = Proficiency.valueOf(import.proficiencies.stealth),
                fromProficiency = import.proficiencies.stealth,
                fromAttribute = import.abilities.dexterity,
                fromItem = import.mods["Stealth"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Survival.name,
                proficiency = Proficiency.valueOf(import.proficiencies.survival),
                fromProficiency = import.proficiencies.survival,
                fromAttribute = import.abilities.wisdom,
                fromItem = import.mods["Survival"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
            CharacterModel.Skill(
                name = Skills.Thievery.name,
                proficiency = Proficiency.valueOf(import.proficiencies.thievery),
                fromProficiency = import.proficiencies.thievery,
                fromAttribute = import.abilities.dexterity,
                fromItem = import.mods["Thievery"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
            ).apply { modifier = this.calculate() },
        )
        import.lores.forEach {
            skills.add(
                CharacterModel.Skill(
                    name = "Lore: " + it[0],
                    proficiency = Proficiency.valueOf(it[1].toInt()),
                    fromProficiency = it[1].toInt(),
                    fromAttribute = import.abilities.intelligence,
                    fromItem = 0,
                    fromLevel = import.level,
                ).apply { modifier = this.calculate() }
            )
        }
        return skills
    }

    private fun getSaves(import: PathbuilderCharacter): List<CharacterModel.Save> {
        return listOf(
            CharacterModel.Save(
                type = SaveType.FORTITUDE,
                proficiency = Proficiency.valueOf(import.proficiencies.fortitude),
                fromProficiency = import.proficiencies.fortitude,
                fromAttribute = import.abilities.constitution,
                fromItem = import.mods["Fortitude"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
                value = 0
            ).apply { value = this.calculate() },
            CharacterModel.Save(
                type = SaveType.REFLEX,
                proficiency = Proficiency.valueOf(import.proficiencies.reflex),
                fromProficiency = import.proficiencies.reflex,
                fromAttribute = import.abilities.dexterity,
                fromItem = import.mods["Reflex"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
                value = 0
            ).apply { value = this.calculate() },
            CharacterModel.Save(
                type = SaveType.WILL,
                proficiency = Proficiency.valueOf(import.proficiencies.will),
                fromProficiency = import.proficiencies.will,
                fromAttribute = import.abilities.wisdom,
                fromItem = import.mods["Will"]?.get("Item Bonus") ?: 0,
                fromLevel = import.level,
                value = 0
            ).apply { value = this.calculate() },
        )
    }

    private suspend fun getClassFeats(feats: List<List<String>>): List<ClassFeatureModel> {
        val classFeats = mutableListOf<ClassFeatureModel>()
        for (feat in feats) {
            if (feat[2].contains("Class Feat")) {
                val name = feat[0]
                val option = feat[1]
                val level = feat[3].toInt()
                val featModel = classFeatRepository.findByName(name).getOrThrow().apply {
                    this.system.takenAtLevel = level
                    this.system.selectedChoice = option
                }

                classFeats.add(featModel)
            }
        }
        return classFeats
    }

    private suspend fun getAncestryFeats(feats: List<List<String>>): List<AncestryFeatureModel> {
        val ancestryFeats = mutableListOf<AncestryFeatureModel>()
        for (feat in feats) {
            if (feat[2].contains("Ancestry Feat")) {
                val name = feat[0]
                val option = feat[1]
                val level = feat[3].toInt()
                val featModel = ancestryFeatRepository.findByName(name).getOrThrow().apply {
                    this.system.takenAtLevel = level
                    this.system.selectedChoice = option
                }

                ancestryFeats.add(featModel)
            }
        }
        return ancestryFeats
    }

    private suspend fun getSkillFeats(feats: List<List<String>>): List<FeatModel> {
        val skillFeats = mutableListOf<FeatModel>()
        for (feat in feats) {
            if (feat[2].contains("Skill Feat")) {
                val name = feat[0]
                val option = feat[1]
                val level = feat[3].toInt()
                val featModel = featRepository.findByName(name).getOrThrow().apply {
                    this.system.takenAtLevel = level
                    this.system.selectedChoice = option
                }

                skillFeats.add(featModel)
            }
        }
        return skillFeats
    }

    private suspend fun getGeneralFeats(feats: List<List<String>>): List<FeatModel> {
        val generalFeats = mutableListOf<FeatModel>()
        for (feat in feats) {
            if (feat[2].contains("General Feat") || feat[2].contains("Awarded Feat")) {
                val name = feat[0]
                val option = feat[1]
                val level = feat[3].toInt()
                val featModel = featRepository.findByName(name).getOrThrow().apply {
                    this.system.takenAtLevel = level
                    this.system.selectedChoice = option
                }

                generalFeats.add(featModel)
            }
        }
        return generalFeats
    }

    private suspend fun getDedications(feats: List<List<String>>): List<DedicationModel> {
        val dedications = mutableListOf<DedicationModel>()
        for (feat in feats) {
            if (feat[2].contains("Archetype Feat") && feat[0].contains("Dedication")) {
                val name = feat[0]
                val option = feat[1]
                val level = feat[3].toInt()
                val featModel = featRepository.findByName(name).getOrThrow().apply {
                    this.system.takenAtLevel = level
                    this.system.selectedChoice = option
                }

                dedications.add(featModel as DedicationModel)
            }
        }
        return dedications
    }

    private suspend fun getDedicationFeats(feats: List<List<String>>): List<FeatModel> {
        val dedicationFeats = mutableListOf<FeatModel>()
        for (feat in feats) {
            if (feat[2].contains("Archetype Feat") && !feat[0].contains("Dedication")) {
                val name = feat[0]
                val option = feat[1]
                val level = feat[3].toInt()
                val featModel = featRepository.findByName(name).getOrThrow().apply {
                    this.system.takenAtLevel = level
                    this.system.selectedChoice = option
                }

                dedicationFeats.add(featModel)
            }
        }
        return dedicationFeats
    }

    private fun getAttributes(attributes: PathbuilderCharacter.Abilities): List<CharacterModel.AbilityScore> {
        val abilityScores = mutableListOf<CharacterModel.AbilityScore>()
        abilityScores.add(CharacterModel.AbilityScore("Strength", attributes.strength))
        abilityScores.add(CharacterModel.AbilityScore("Dexterity", attributes.dexterity))
        abilityScores.add(CharacterModel.AbilityScore("Constitution", attributes.constitution))
        abilityScores.add(CharacterModel.AbilityScore("Intelligence", attributes.intelligence))
        abilityScores.add(CharacterModel.AbilityScore("Wisdom", attributes.wisdom))
        abilityScores.add(CharacterModel.AbilityScore("Charisma", attributes.charisma))
        return abilityScores
    }
}
