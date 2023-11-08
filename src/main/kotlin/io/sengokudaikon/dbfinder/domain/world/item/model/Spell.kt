package io.sengokudaikon.dbfinder.domain.world.item.model

import io.sengokudaikon.dbfinder.domain.character.feat.model.Item
import io.sengokudaikon.dbfinder.infrastructure.enums.DamageDice
import io.sengokudaikon.dbfinder.infrastructure.enums.DamageType
import io.sengokudaikon.dbfinder.infrastructure.enums.SavingThrows
import kotlinx.serialization.Serializable

@Serializable
data class Spell(
    val name: String,
    val description: String,
    val level: Int,
    val castTime: String,
    val range: String,
    val cost: String,
    val trigger: String,
    val requirements: String,
    val targets: String,
    val duration: String,
    val saves: SavingThrows?,
    val rarity: io.sengokudaikon.dbfinder.infrastructure.enums.Rarity,
    val damageDice: DamageDice?,
    val damageType: DamageType?,
    val contentSrc: String,
    val traits: List<SpellTrait>,
    val effects: List<SpellEffect>,
) : Item
