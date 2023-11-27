package io.sengokudaikon.isn.chargen.persistence.entity

import io.sengokudaikon.isn.compendium.persistence.world.entity.Feats
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterFeats : KotlinxUUIDTable("char_feats") {
    val characterID = reference("characterID", Characters)
    val featID = reference("featID", Feats)
    val takenAtLevel = integer("takenAtLevel")
}
