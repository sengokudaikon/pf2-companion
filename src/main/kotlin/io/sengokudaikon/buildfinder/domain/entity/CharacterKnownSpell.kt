package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterKnownSpells
import io.sengokudaikon.dbfinder.domain.world.item.entity.Spell
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterKnownSpell(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterKnownSpell>(CharacterKnownSpells)

    var character by Character referencedOn CharacterKnownSpells.characterID
    var spell by Spell referencedOn CharacterKnownSpells.spellID
}
