package io.sengokudaikon.isn.compendium.domain.world.item.model

import io.sengokudaikon.isn.compendium.domain.character.feat.model.Item
import io.sengokudaikon.isn.compendium.enums.DamageDice
import io.sengokudaikon.isn.compendium.enums.DamageType
import io.sengokudaikon.isn.compendium.enums.SavingThrows
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
    val rarity: io.sengokudaikon.isn.compendium.enums.Rarity,
    val damageDice: DamageDice?,
    val damageType: DamageType?,
    val contentSrc: String,
    val traits: List<SpellTrait>,
    val effects: List<SpellEffect>,
) : Item
