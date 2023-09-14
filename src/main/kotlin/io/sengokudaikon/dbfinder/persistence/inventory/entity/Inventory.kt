package io.sengokudaikon.dbfinder.persistence.inventory.entity

import io.sengokudaikon.buildfinder.persistence.entity.Characters
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Inventory : KotlinxUUIDTable("char_inventory") {
    val characterID = reference("characterID", Characters)
    val equippedArmourID = reference("equippedArmourID", Armours).nullable()
    val equippedWeaponID = reference("equippedWeaponID", Weapons).nullable()
    val platinum = integer("platinum")
    val gold = integer("gold")
    val silver = integer("silver")
    val copper = integer("copper")
}
