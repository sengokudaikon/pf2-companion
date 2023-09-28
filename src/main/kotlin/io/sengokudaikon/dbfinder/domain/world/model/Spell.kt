package io.sengokudaikon.dbfinder.domain.world.model

import io.sengokudaikon.dbfinder.domain.character.feat.model.Item
import kotlinx.serialization.Serializable

@Serializable
data class Spell(
    val name: String,
    val description: String,
    val level: Int,
    val school: String,
    val castTime: String,
    val components: String,
    val range: String,
    val cost: String,
    val trigger: String,
    val requirements: String,
    val targets: String,
    val duration: String,
    val saves: io.sengokudaikon.dbfinder.infrastructure.enums.SavingThrows,
    val rarity: io.sengokudaikon.dbfinder.infrastructure.enums.Rarity,
    val damageDice: io.sengokudaikon.dbfinder.infrastructure.enums.DamageDice?,
    val damageType: io.sengokudaikon.dbfinder.infrastructure.enums.DamageType?,
    val heightenEvery: Int?,
    val heightenDice: io.sengokudaikon.dbfinder.infrastructure.enums.DamageDice?,
    val heightenMultiplier: Int?,
    val isFocus: Boolean,
    val contentSrc: String,
    val isArchived: Boolean = false,
    val homebrewID: Int? = null,
) : Item
