package io.sengokudaikon.isn.chargen.persistence.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object CharacterAttributes : KotlinxUUIDTable("char_attributes") {
    val characterID = reference("characterID", Characters)
    val attribute = enumerationByName<io.sengokudaikon.isn.compendium.enums.Ability>("attribute", 10)
    val value = integer("value")
}
