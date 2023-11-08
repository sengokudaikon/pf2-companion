package io.sengokudaikon.dbfinder.domain.world.global.model

import org.slf4j.LoggerFactory
import java.util.*

enum class TraitType {
    GENERAL,
    ANCESTRY,
    ALIGNMENT,
    AP,
    ARMOUR,
    CLASS,
    CLASSSPECIFIC,
    CREATURE,
    ELEMENTAL,
    ENERGY,
    EQUIPMENT,
    HAZARD,
    KINGDOM,
    MONSTER,
    PLANAR,
    POISON,
    RARITY,
    SCHOOL,
    SENSE,
    SETTLEMENT,
    SHIELD,
    TRADITION,
    VARIANT,
    WEAPON,
}

fun String.toTraitType(): TraitType {
    return try {
        TraitType.valueOf(this.uppercase(Locale.getDefault()))
    } catch (e: IllegalArgumentException) {
        val logger = LoggerFactory.getLogger("TraitType")
        logger.warn("TraitType $this not found, defaulting to GENERAL", e)
        TraitType.GENERAL
    }
}
