package io.sengokudaikon.dbfinder.persistence.world.entity

import io.sengokudaikon.dbfinder.persistence.enums.DamageDice
import io.sengokudaikon.dbfinder.persistence.enums.DamageType
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.SavingThrows
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Spells : KotlinxUUIDTable("spells") {
    val name = varchar("name", 50)
    val level = integer("level")
    val school = varchar("school", 50)
    val castTime = varchar("castTime", 50)
    val components = varchar("components", 50)
    val range = varchar("range", 50)
    val cost = varchar("cost", 50)
    val trigger = varchar("trigger", 50)
    val requirements = varchar("requirements", 50)
    val targets = varchar("targets", 50)
    val duration = varchar("duration", 50)
    val saves = enumerationByName<SavingThrows>("saves", length = 20)
    val rarity = enumerationByName<Rarity>("rarity", length = 10)
    val description = text("description")
    val damageDice = enumerationByName<DamageDice>("damageDice", length = 10).nullable()
    val damageType = enumerationByName<DamageType>("damageType", length = 10).nullable()
    val heightenEvery = integer("heightenEvery").nullable()
    val heightenDice = enumerationByName<DamageDice>("heightenDice", length = 10).nullable()
    val heightenMultiplier = integer("heightenMultiplier").nullable()
    val isFocus = bool("isFocus").default(false)
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", length = 100)
    val homebrewID = integer("homebrew_id").nullable().default(null)
    val version = varchar("version", length = 10)
}
