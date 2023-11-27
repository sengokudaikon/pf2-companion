package io.sengokudaikon.isn.compendium.domain.character.background.entity

import io.sengokudaikon.isn.compendium.domain.character.feat.entity.Feat
import io.sengokudaikon.isn.compendium.domain.character.feat.model.Item
import io.sengokudaikon.isn.compendium.domain.world.action.entity.Action
import io.sengokudaikon.isn.compendium.domain.world.item.entity.Spell
import io.sengokudaikon.isn.compendium.persistence.character.background.entity.BackgroundItems
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BackgroundItem(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    fun toModel(): Item {
        return when {
            this.feat != null -> {
                this.feat!!.toModel()
            }

            this.action != null -> {
                this.action!!.toModel()
            }

            this.spell != null -> {
                this.spell!!.toModel()
            }

            else -> {
                throw DatabaseException.NotFound("No item found")
            }
        }
    }

    companion object : KotlinxUUIDEntityClass<BackgroundItem>(BackgroundItems)

    var background by Background referencedOn BackgroundItems.background
    var feat by Feat optionalReferencedOn BackgroundItems.feat
    var action by Action optionalReferencedOn BackgroundItems.action
    var spell by Spell optionalReferencedOn BackgroundItems.spell
}