package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.persistence.items.entity.Armours
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterArmours : KotlinxUUIDTable("char_armours") {
    val characterID = reference("characterID", Characters)
    val armourID = reference("armourID", Armours)
}
