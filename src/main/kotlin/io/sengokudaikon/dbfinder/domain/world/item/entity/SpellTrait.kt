package io.sengokudaikon.dbfinder.domain.world.item.entity

import io.sengokudaikon.dbfinder.domain.world.global.entity.Trait
import io.sengokudaikon.dbfinder.persistence.items.entity.SpellTraits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SpellTrait(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<SpellTrait>(SpellTraits)

    var trait by Trait referencedOn SpellTraits.traitID
    var spell by Spell referencedOn SpellTraits.spellID
    fun toModel(): io.sengokudaikon.dbfinder.domain.world.item.model.SpellTrait {
        return io.sengokudaikon.dbfinder.domain.world.item.model.SpellTrait(
            name = trait.name,
        )
    }
}
