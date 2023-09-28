package io.sengokudaikon.dbfinder.domain.character.background.entity

import io.sengokudaikon.dbfinder.domain.character.feat.entity.Feat
import io.sengokudaikon.dbfinder.domain.character.feat.model.Item
import io.sengokudaikon.dbfinder.domain.world.entity.Action
import io.sengokudaikon.dbfinder.domain.world.entity.Spell
import io.sengokudaikon.dbfinder.persistence.character.background.entity.BackgroundItems
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
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
