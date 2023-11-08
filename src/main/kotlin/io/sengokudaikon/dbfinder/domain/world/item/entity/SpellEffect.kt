package io.sengokudaikon.dbfinder.domain.world.item.entity

import io.sengokudaikon.dbfinder.domain.world.effect.entity.Effect
import io.sengokudaikon.dbfinder.persistence.items.entity.SpellEffects
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SpellEffect(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<SpellEffect>(SpellEffects)

    var spellID by Spell referencedOn SpellEffects.spellID
    var effect by Effect referencedOn SpellEffects.effectID
    fun toModel(): io.sengokudaikon.dbfinder.domain.world.item.model.SpellEffect {
        return io.sengokudaikon.dbfinder.domain.world.item.model.SpellEffect(
            name = effect.name,
        )
    }
}
