package io.sengokudaikon.isn.compendium.domain.character.ancestry.entity

import io.sengokudaikon.isn.compendium.domain.world.global.entity.Trait
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryTraits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.domain.world.global.model.Trait as ModelTrait

class AncestryTrait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryTrait>(AncestryTraits)

    var ancestryID by Ancestry referencedOn AncestryTraits.ancestryID
    var trait by Trait referencedOn AncestryTraits.trait

    fun toModel(): ModelTrait {
        return trait.toModel()
    }
}
