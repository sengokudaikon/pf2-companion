package io.sengokudaikon.dbfinder.domain.character.ancestry.entity

import io.sengokudaikon.dbfinder.domain.world.entity.Trait
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryTraits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.model.Trait as ModelTrait

class AncestryTrait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryTrait>(AncestryTraits)
    var ancestryID by Ancestry referencedOn AncestryTraits.ancestryID
    var trait by Trait referencedOn AncestryTraits.trait

    fun toModel(): ModelTrait {
        return trait.toModel()
    }
}
