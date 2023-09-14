package io.sengokudaikon.dbfinder.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Traits : KotlinxUUIDTable("traits") {
    val name = varchar("name", 50)
    val description = text("description")
    val isImportant = bool("is_important").default(false)
    val isHidden = bool("is_hidden").default(false)
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("source", 50)
    val homebrewID = integer("homebrew_id").nullable().default(null)
}
