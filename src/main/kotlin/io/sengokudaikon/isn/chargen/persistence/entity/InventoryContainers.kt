package io.sengokudaikon.isn.chargen.persistence.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object InventoryContainers : KotlinxUUIDTable("char_inventory_containers") {
    val inventoryID = reference("inventory_id", Inventory)
    val containerType =
        enumerationByName<ContainerType>(
            "container_type",
            length = 20,
        )

    enum class ContainerType {
        BAG_OF_HOLDING,
        BACKPACK,
    }
}
