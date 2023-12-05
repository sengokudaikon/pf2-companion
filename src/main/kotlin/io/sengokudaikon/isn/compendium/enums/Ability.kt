package io.sengokudaikon.isn.compendium.enums

enum class Ability {
    None, Anything, Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma
}

fun fromShortName(shortName: String): Ability {
    return when (shortName) {
        "str" -> Ability.Strength
        "dex" -> Ability.Dexterity
        "con" -> Ability.Constitution
        "int" -> Ability.Intelligence
        "wis" -> Ability.Wisdom
        "cha" -> Ability.Charisma
        else -> Ability.None
    }
}
