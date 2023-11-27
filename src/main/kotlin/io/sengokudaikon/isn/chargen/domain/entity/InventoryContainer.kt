package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.chargen.persistence.entity.InventoryContainers
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class InventoryContainer(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object :
        KotlinxUUIDEntityClass<InventoryContainer>(InventoryContainers)

    var inventory by CharacterInventory referencedOn InventoryContainers.inventoryID
    val containerType by InventoryContainers.containerType
}
