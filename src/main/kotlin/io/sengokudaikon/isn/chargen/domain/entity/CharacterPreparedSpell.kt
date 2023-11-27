package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.chargen.persistence.entity.CharacterPreparedSpells
import io.sengokudaikon.isn.compendium.domain.world.item.entity.Spell
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterPreparedSpell(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterPreparedSpell>(CharacterPreparedSpells)

    var character by Character referencedOn CharacterPreparedSpells.characterID
    var spell by Spell referencedOn CharacterPreparedSpells.spellID
    var level by CharacterPreparedSpells.preparedLevel
}
