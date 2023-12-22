package io.sengokudaikon.isn.compendium.enums

enum class Ability {
    None, Anything, Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma;

    companion object {
        fun fromShortName(shortName: String): Ability {
            return when (shortName) {
                "str" -> Strength
                "dex" -> Dexterity
                "con" -> Constitution
                "int" -> Intelligence
                "wis" -> Wisdom
                "cha" -> Charisma
                else -> None
            }
        }
    }
}
