package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import io.sengokudaikon.dbfinder.operations.character.dto.Ability
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Classes : KotlinxUUIDTable("char_classes") {
    val name = varchar("name", 50)
    val rarity = enumerationByName<Rarity>("rarity", length = 20)
    val description = text("description")
    val keyAttribute = enumerationByName<Ability>("keyAttribute", length = 20)
    val hitPoints = integer("hitPoints")
    val classDC = integer("classDC")
    val code = varchar("code", 10)
    val contentSrc = varchar("content_src", 100)
    val homebrewID = integer("homebrew_id").nullable().default(null)
    val version = integer("version")
}
