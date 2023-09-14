package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.character.feats.entity.Feats
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterFeats : KotlinxUUIDTable("char_feats") {
    val characterID = reference("characterID", Characters)
    val featID = reference("featID", Feats)
    val takenAtLevel = integer("takenAtLevel")
}
