package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.chargen.persistence.entity.CharacterArmours
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterGears
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterWeapons
import io.sengokudaikon.isn.chargen.persistence.entity.Inventory
import io.sengokudaikon.isn.chargen.persistence.entity.InventoryContainers
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterInventory(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterInventory>(Inventory)

    var character by Character referencedOn Inventory.characterID
    val containers by InventoryContainer referrersOn InventoryContainers.inventoryID
    val gear by CharacterGear referrersOn CharacterGears.characterID
    val equippedWeapons by CharacterWeapon referrersOn CharacterWeapons.characterID
    val equippedArmor by CharacterArmour referencedOn CharacterArmours.characterID
    val investedItems by CharacterGear referrersOn CharacterGears.characterID
    var platinum by Inventory.platinum
    var gold by Inventory.gold
    var silver by Inventory.silver
    var copper by Inventory.copper
}