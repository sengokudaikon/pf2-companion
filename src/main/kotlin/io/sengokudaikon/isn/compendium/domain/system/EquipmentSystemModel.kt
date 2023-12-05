package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import kotlinx.serialization.json.JsonObject

interface EquipmentSystemModel : SystemModel {
    val baseItem: String?
    val containerId: String?
    val equippedBulk: JsonObject
    val hardness: Int
    val hp: HP
    val level: JsonObject
    val material: Material?
    val negateBulk: JsonObject
    val price: Price
    val quantity: Int
    val size: String
    val stackGroup: String?
    val usage: JsonObject
    val weight: JsonObject
}
