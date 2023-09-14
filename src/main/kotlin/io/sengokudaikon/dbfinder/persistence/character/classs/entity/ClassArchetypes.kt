package io.sengokudaikon.dbfinder.persistence.character.classs.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object ClassArchetypes : KotlinxUUIDTable("char_class_archetypes") {
    val name = varchar("name", 255)
    val description = text("description")
    val dedicatedFeatName = varchar("dedicatedFeatName", 255)
    val contentSrc = varchar("content_src", 100)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
