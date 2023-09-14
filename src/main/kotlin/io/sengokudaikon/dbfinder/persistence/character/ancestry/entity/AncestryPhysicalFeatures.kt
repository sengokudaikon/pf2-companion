package io.sengokudaikon.dbfinder.persistence.character.ancestry.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object AncestryPhysicalFeatures : KotlinxUUIDTable("char_ancestry_physical_features") {
    val ancestryID = reference("ancestry_id", Ancestries)
    val img = varchar("img", 255)
    val level = integer("level")
    val name = varchar("name", 255)
    val description = text("description")
}
