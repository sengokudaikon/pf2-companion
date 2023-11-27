package io.sengokudaikon.isn.compendium.domain.character.background.entity

import io.sengokudaikon.isn.compendium.domain.world.global.entity.Trait
import io.sengokudaikon.isn.compendium.persistence.character.background.entity.BackgroundTraits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.domain.world.global.model.Trait as ModelTrait

class BackgroundTrait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<BackgroundTrait>(BackgroundTraits)

    var backgroundId by Background referencedOn BackgroundTraits.background
    var traitId by Trait referencedOn BackgroundTraits.trait

    fun toModel(): ModelTrait {
        return traitId.toModel()
    }
}
