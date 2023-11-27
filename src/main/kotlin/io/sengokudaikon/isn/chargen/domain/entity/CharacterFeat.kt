package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.compendium.domain.character.feat.entity.Feat
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterFeat(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object :
        KotlinxUUIDEntityClass<CharacterFeat>(io.sengokudaikon.isn.chargen.persistence.entity.CharacterFeats)

    var character by Character referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterFeats.characterID
    var feat by Feat referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterFeats.featID
    var takenAtLevel by io.sengokudaikon.isn.chargen.persistence.entity.CharacterFeats.takenAtLevel
}
