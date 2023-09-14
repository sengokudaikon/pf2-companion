package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterFeats
import io.sengokudaikon.dbfinder.domain.character.feat.entity.Feat
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterFeat(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterFeat>(CharacterFeats)
    var character by Character referencedOn CharacterFeats.characterID
    var feat by Feat referencedOn CharacterFeats.featID
    var takenAtLevel by CharacterFeats.takenAtLevel
}
