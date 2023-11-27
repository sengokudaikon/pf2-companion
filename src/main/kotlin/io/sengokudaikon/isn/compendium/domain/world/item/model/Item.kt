package io.sengokudaikon.isn.compendium.domain.world.item.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val description: String,
    val price: Int,
    val bulk: Int,
    val level: Int,
    val rarity: io.sengokudaikon.isn.compendium.enums.Rarity,
    val itemType: io.sengokudaikon.isn.compendium.enums.ItemType,
    val craftRequirements: String,
    val usage: String,
    val hands: io.sengokudaikon.isn.compendium.enums.ItemHands,
    val size: io.sengokudaikon.isn.compendium.enums.Size,
)
