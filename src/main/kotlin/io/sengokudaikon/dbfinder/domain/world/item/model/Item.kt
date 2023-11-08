package io.sengokudaikon.dbfinder.domain.world.item.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val description: String,
    val price: Int,
    val bulk: Int,
    val level: Int,
    val rarity: io.sengokudaikon.dbfinder.infrastructure.enums.Rarity,
    val itemType: io.sengokudaikon.dbfinder.infrastructure.enums.ItemType,
    val craftRequirements: String,
    val usage: String,
    val hands: io.sengokudaikon.dbfinder.infrastructure.enums.ItemHands,
    val size: io.sengokudaikon.dbfinder.infrastructure.enums.Size,
)
