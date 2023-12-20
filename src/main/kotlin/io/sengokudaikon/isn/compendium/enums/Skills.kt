package io.sengokudaikon.isn.compendium.enums

enum class Skills {
    Any,
    Acrobatics,
    Arcana,
    Athletics,
    Crafting,
    Deception,
    Diplomacy,
    Intimidation,
    Medicine,
    Nature,
    Occultism,
    Performance,
    Religion,
    Society,
    Stealth,
    Survival,
    Thievery,
    Lore,
    ;

    companion object {
        fun from(value: String): Skills {
            return when (value) {
                "acr" -> Acrobatics
                "arc" -> Arcana
                "ath" -> Athletics
                "cra" -> Crafting
                "dec" -> Deception
                "dip" -> Diplomacy
                "int" -> Intimidation
                "med" -> Medicine
                "nat" -> Nature
                "occ" -> Occultism
                "per" -> Performance
                "rel" -> Religion
                "soc" -> Society
                "ste" -> Stealth
                "sur" -> Survival
                "thi" -> Thievery
                "lor" -> Lore
                else -> Any
            }
        }
    }
}
