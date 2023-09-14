package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterGears
import io.sengokudaikon.dbfinder.persistence.inventory.entity.Inventory
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterInventory(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterInventory>(Inventory)

    var character by Character referencedOn Inventory.characterID
    var equippedArmour by CharacterArmour optionalReferencedOn Inventory.equippedArmourID
    var equippedWeapon by CharacterWeapon optionalReferencedOn Inventory.equippedWeaponID
    val gear by CharacterGear referrersOn CharacterGears.characterID
    var platinum by Inventory.platinum
    var gold by Inventory.gold
    var silver by Inventory.silver
    var copper by Inventory.copper
}
