package io.sengokudaikon.isn.compendium.persistence.items.entity

import io.sengokudaikon.isn.compendium.enums.DamageDice
import io.sengokudaikon.isn.compendium.enums.DamageType
import io.sengokudaikon.isn.compendium.enums.Rarity
import io.sengokudaikon.isn.compendium.enums.SavingThrows
import io.sengokudaikon.isn.compendium.persistence.world.entity.Effects
import io.sengokudaikon.isn.compendium.persistence.world.entity.Traits
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Spells : KotlinxUUIDTable("spells") {
    val name = varchar("name", 50)
    val level = integer("level")
    val castTime = varchar("castTime", 50)
    val range = varchar("range", 50)
    val cost = varchar("cost", 50)
    val trigger = varchar("trigger", 50)
    val requirements = varchar("requirements", 50)
    val targets = varchar("targets", 50)
    val duration = varchar("duration", 50)
    val saves = enumerationByName<SavingThrows>("saves", length = 20).nullable().default(null)
    val rarity = enumerationByName<Rarity>("rarity", length = 10)
    val description = text("description")
    val damageDice = enumerationByName<DamageDice>(
        "damage_dice",
        length = 10,
    ).nullable()
    val damageType = enumerationByName<DamageType>(
        "damage_type",
        length = 10,
    ).nullable()
    val contentSrc = varchar("content_src", length = 100)
}

object SpellTraits : KotlinxUUIDTable("spell_traits") {
    val spellID = reference("spell_id", Spells)
    val traitID = reference("trait_id", Traits)
}

object SpellEffects : KotlinxUUIDTable("spell_effects") {
    val spellID = reference("spell_id", Spells)
    val effectID = reference("effect_id", Effects)
}
