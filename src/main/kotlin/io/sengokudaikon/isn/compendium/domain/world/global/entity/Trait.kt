package io.sengokudaikon.isn.compendium.domain.world.global.entity

import io.sengokudaikon.isn.compendium.persistence.world.entity.Traits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.domain.world.global.model.Trait as ModelTrait

class Trait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Trait>(Traits)

    var name by Traits.name
    var description by Traits.description
    var type by Traits.type
    var isHidden by Traits.isHidden
    var isArchived by Traits.isArchived
    var contentSrc by Traits.contentSrc

    fun toModel(): ModelTrait {
        return ModelTrait(
            this.name,
            this.description,
            this.contentSrc,
            this.type,
        )
    }
}
