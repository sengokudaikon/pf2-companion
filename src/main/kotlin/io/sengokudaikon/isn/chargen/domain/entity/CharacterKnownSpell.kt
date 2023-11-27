package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.chargen.persistence.entity.CharacterKnownSpells
import io.sengokudaikon.isn.compendium.domain.world.item.entity.Spell
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterKnownSpell(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object :
        KotlinxUUIDEntityClass<CharacterKnownSpell>(CharacterKnownSpells)

    var character by Character referencedOn CharacterKnownSpells.characterID
    var spell by Spell referencedOn CharacterKnownSpells.spellID
}
