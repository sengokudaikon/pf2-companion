package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterSpellSlots
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterSpellSlot(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterSpellSlot>(CharacterSpellSlots)

    var character by Character referencedOn CharacterSpellSlots.characterID
    var spellLevel by CharacterSpellSlots.spellLevel
    var maxSlots by CharacterSpellSlots.maxSlots
    var usedSlots by CharacterSpellSlots.usedSlots
}
