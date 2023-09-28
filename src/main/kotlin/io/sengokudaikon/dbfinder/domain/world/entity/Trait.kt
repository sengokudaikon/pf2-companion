package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Traits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Trait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Trait>(Traits)

    var name by Traits.name
    var description by Traits.description
    var type by Traits.type
    var isHidden by Traits.isHidden
    var isArchived by Traits.isArchived
    var contentSrc by Traits.contentSrc
    val homebrewID by Traits.homebrewID

    fun toModel(): io.sengokudaikon.dbfinder.domain.world.model.Trait {
        return io.sengokudaikon.dbfinder.domain.world.model.Trait(
            this.name,
            this.description,
            this.contentSrc,
            this.type,
            this.isHidden,
            this.isArchived,
            this.homebrewID,
        )
    }
}
