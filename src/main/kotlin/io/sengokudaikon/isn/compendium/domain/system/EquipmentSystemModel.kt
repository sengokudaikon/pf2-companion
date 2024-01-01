package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.compendium.domain.equipment.dto.HP
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Material
import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import org.bson.BsonValue

interface EquipmentSystemModel : SystemModel {
    val baseItem: String?
    val bulk: BsonValue?
    val containerId: String?
    val hardness: Int
    val hp: HP
    val level: BsonValue
    val material: Material?
    val price: Price
    val quantity: Int
    val size: String
    val usage: BsonValue?
}
