package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Items
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.model.Item as ModelItem

class Item(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Item>(Items)

    var name by Items.name
    var description by Items.description
    var price by Items.price
    var bulk by Items.bulk
    var level by Items.level
    var rarity by Items.rarity
    var itemType by Items.itemType
    var craftRequirements by Items.craftRequirements
    var usage by Items.usage
    var hands by Items.hands
    var size by Items.size
    var homebrewID by Items.homebrewID

    fun toModel(): ModelItem {
        return ModelItem(
            name = this.name,
            description = this.description,
            price = this.price,
            bulk = this.bulk,
            level = this.level,
            rarity = this.rarity,
            itemType = this.itemType,
            craftRequirements = this.craftRequirements,
            usage = this.usage,
            hands = this.hands,
            size = this.size,
            homebrewID = this.homebrewID,
        )
    }
}
