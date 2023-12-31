package io.sengokudaikon.isn.compendium.operations.search.dto

import kotlinx.serialization.Serializable

@Serializable
sealed class FilterType(open val dbField: String, open val applicableTypes: List<String> = emptyList()) {
    data object Type : FilterType("type")
    data object Level : FilterType("system.level")
    data object Description : FilterType("system.description.value")
    data object Rarity : FilterType("system.traits.rarity")
    data object Publication : FilterType("system.publication.title")
    data object Traits : FilterType("system.traits.value")
    data object Cost : FilterType("system.price.value.gp", listOf("equipment", "armor", "weapon", "shield"))
    data object Weight : FilterType("system.bulk.value", listOf("equipment", "armor", "weapon", "shield"))
    data object DamageDie : FilterType("system.damage.die", listOf("spell", "weapon"))
    data object DamageType : FilterType("system.damage.damageType", listOf("spell", "weapon"))
    data object ArmorClass : FilterType("system.acBonus", listOf("armor", "equipment", "shield"))
    data object HitPoints : FilterType("system.hp.value")
    data object Skill : FilterType("system.prerequisites.value", listOf("feat"))
    data object Range : FilterType("system.range", listOf("spell", "weapon"))
    data object Category : FilterType("system.category", listOf("feat", "equipment", "armor", "consumable", "shield", "weapon"))
    data object Group : FilterType("system.group", listOf("equipment", "armor", "consumable", "shield", "weapon"))
    data object Speed : FilterType("system.speed.value")
    data object Weakness : FilterType("system.weaknesses.value")
    data object Resistance : FilterType("system.resistances.value")


    sealed class Requirement(override val dbField: String) : FilterType(dbField) {
        data object Strength : Requirement("system.strength")
        data object Dexterity : Requirement("system.dexCap")
        // Add other attribute types here...
    }

    sealed class Save(override val dbField: String) : FilterType(dbField) {
        data object Fortitude : Save("system.saves.fortitude")
        data object Reflex : Save("system.saves.reflex")
        data object Will : Save("system.saves.will")
    }

    companion object {
        fun fromString(value: String): FilterType {
            return when (value.lowercase()) {
                "type" -> Type
                "level" -> Level
                "rarity" -> Rarity
                "publication" -> Publication
                "traits" -> Traits
                "price" -> Cost
                "bulk" -> Weight
                "damageDie" -> DamageDie
                "damageType" -> DamageType
                "ac" -> ArmorClass
                "hp" -> HitPoints
                "skill" -> Skill
                "range" -> Range
                "category" -> Category
                "group" -> Group
                "speed" -> Speed
                "weakness" -> Weakness
                "resistance" -> Resistance
                "strength" -> Requirement.Strength
                "dexCap" -> Requirement.Dexterity
                "fortitude" -> Save.Fortitude
                "reflex" -> Save.Reflex
                "will" -> Save.Will
                else -> throw IllegalArgumentException("Invalid filter type: $value")
            }
        }
    }
}