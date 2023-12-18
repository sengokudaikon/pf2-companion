package io.sengokudaikon.isn.chargen.domain.model

import io.sengokudaikon.isn.compendium.enums.Proficiency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PathbuilderCharacter(
    val name: String,
    @SerialName("class")
    val classs: String,
    val dualClass: String?,
    val level: Int,
    val ancestry: String,
    val heritage: String,
    val background: String,
    val alignment: String,
    val gender: String,
    val age: String,
    val deity: String,
    val size: Int,
    val sizeName: String,
    val keyability: String,
    val languages: List<String>,
    val rituals: List<String>,
    val resistances: List<String>,
    val attributes: Attributes,
    val abilities: Abilities,
    val proficiencies: Proficiencies,
    val mods: Map<String, Map<String, Int>>,
    val feats: List<List<String>>,
    val specials: List<String>,
    val lores: List<List<String>>,
    val equipmentContainers: Map<String, Container>,
    val equipment: List<List<String>>,
    val specificProficiencies: Map<String, List<String>>,
    val weapons: List<Weapon>,
    val money: Money,
    val armor: List<Armor>,
    val spellCasters: List<SpellCaster>,
    val focusPoints: Int,
    val focus: Map<String, Map<String, FocusSpellcasting>>,
    val formula: List<Formula>,
    val acTotal: ACTotal,
    val pets: List<Pet>,
    val familiars: List<Familiar>
) {
    @Serializable
    data class Familiar(
        val type: String,
        val name: String,
        val equipment: List<String>,
        val specific: String?,
        val abilities: List<String>,
    )

    @Serializable
    data class Pet(
        val type: String,
        val name: String,
        val equipment: List<String>,
        val mature: Boolean,
        val incredible: Boolean,
        val incredibleType: String,
        val specializations: List<String>,
        val armor: String
    )
    @Serializable
    data class Formula(
        val type: String,
        val known: List<String>
    )
    @Serializable
    data class ACTotal(
        val acProfBonus: Int,
        val acAbilityBonus: Int,
        val acItemBonus: Int,
        val acTotal: Int,
        val shieldBonus: Int,
    )
    @Serializable
    data class FocusSpellcasting(
        val abilityBonus: Int,
        val proficiency: Int,
        val itemBonus: Int,
        val focusCantrips: List<String>,
        val focusSpells: List<String>,
    )
    @Serializable
    data class SpellCaster(
        val name: String,
        val magicTradition: String,
        val spellcastingType: String,
        val ability: String,
        val proficiency: Int,
        val focusPoints: Int,
        val innate: Boolean,
        val perDay: List<Int>,
        val spells: List<Spell>,
        val prepared: List<Spell>,
        val blendedSpells: List<Spell>,
    ) {
        @Serializable
        data class Spell(
            val spellLevel: Int,
            val list: List<String>,
        )
    }
    @Serializable
    data class Money(
        val cp: Int,
        val sp: Int,
        val gp: Int,
        val pp: Int,
    )

    @Serializable
    data class Armor(
        val name: String,
        val qty: Int,
        val prof: String,
        val pot: String,
        val res: String,
        val mat: String,
        val display: String,
        val worn: Boolean,
        val runes: List<String>,
    )
    @Serializable
    data class Weapon(
        val name: String,
        val qty: Int,
        val prof: String,
        val die: String,
        val pot: String,
        val str: String,
        val mat: String,
        val display: String,
        val runes: List<String>,
        val damageType: String,
        val attack: String,
        val damageBonus: String,
        val extraDamage: List<String>,
    )
    @Serializable
    data class Container(
        val containerName: String,
        val bagOfHolding: Boolean,
        val backpack: Boolean,
    )
    @Serializable
    data class Abilities(
        val strength: Int,
        val dexterity: Int,
        val constitution: Int,
        val intelligence: Int,
        val wisdom: Int,
        val charisma: Int,
        val breakdown: Breakdown,
    ) {
        @Serializable
        data class Breakdown(
            val ancestryFree: List<String?>,
            val ancestryBoosts: List<String?>,
            val ancestryFlaws: List<String?>,
            val backgroundBoosts: List<String?>,
            val classBoosts: List<String?>,
            val mapLevelledBoosts: Map<String, List<String>>,
        )
    }

    fun calculateProficiencies(value: Int): Proficiency {
        return when (value) {
            0 -> Proficiency.UNTRAINED
            2 -> Proficiency.TRAINED
            4 -> Proficiency.EXPERT
            6 -> Proficiency.MASTER
            8 -> Proficiency.LEGENDARY
            else -> Proficiency.UNTRAINED
        }
    }

    @Serializable
    data class Proficiencies(
        val classDC: Int,
        val perception: Int,
        val fortitude: Int,
        val reflex: Int,
        val will: Int,
        val heavy: Int,
        val medium: Int,
        val light: Int,
        val unarmored: Int,
        val advanced: Int,
        val martial: Int,
        val simple: Int,
        val unarmed: Int,
        val castingArcane: Int,
        val castingDivine: Int,
        val castingOccult: Int,
        val castingPrimal: Int,
        val acrobatics: Int,
        val arcana: Int,
        val athletics: Int,
        val crafting: Int,
        val deception: Int,
        val diplomacy: Int,
        val intimidation: Int,
        val medicine: Int,
        val nature: Int,
        val occultism: Int,
        val performance: Int,
        val religion: Int,
        val society: Int,
        val stealth: Int,
        val survival: Int,
        val thievery: Int,
    )

    @Serializable
    data class Attributes(
        val ancestryHp: Int,
        val classHp: Int,
        val bonusHp: Int,
        val bonusHpPerLevel: Int,
        val speed: Int,
        val speedBonus: Int,
    )
}