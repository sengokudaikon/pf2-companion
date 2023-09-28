package io.sengokudaikon.buildfinder.persistence.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.Ability
import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterAttributes : KotlinxUUIDTable("char_attributes") {
    val characterID = reference("characterID", Characters)
    val attribute = enumerationByName<Ability>("attribute", 10)
    val value = integer("value")
}
