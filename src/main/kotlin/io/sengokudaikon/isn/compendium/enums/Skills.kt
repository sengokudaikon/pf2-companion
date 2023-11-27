package io.sengokudaikon.isn.compendium.enums

enum class Skills {
    ANY,
    ACROBATICS,
    ARCANA,
    ATHLETICS,
    CRAFTING,
    DECEPTION,
    DIPLOMACY,
    INTIMIDATION,
    MEDICINE,
    NATURE,
    OCCULTISM,
    PERFORMANCE,
    RELIGION,
    SOCIETY,
    STEALTH,
    SURVIVAL,
    THIEVERY,
    LORE,
    ;

    companion object {
        fun from(value: String): Skills {
            return when (value) {
                "acr" -> ACROBATICS
                "arc" -> ARCANA
                "ath" -> ATHLETICS
                "cra" -> CRAFTING
                "dec" -> DECEPTION
                "dip" -> DIPLOMACY
                "int" -> INTIMIDATION
                "med" -> MEDICINE
                "nat" -> NATURE
                "occ" -> OCCULTISM
                "per" -> PERFORMANCE
                "rel" -> RELIGION
                "soc" -> SOCIETY
                "ste" -> STEALTH
                "sur" -> SURVIVAL
                "thi" -> THIEVERY
                "lor" -> LORE
                else -> ANY
            }
        }
    }
}
