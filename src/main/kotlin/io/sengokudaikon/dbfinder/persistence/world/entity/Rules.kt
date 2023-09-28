package io.sengokudaikon.dbfinder.persistence.world.entity

import io.sengokudaikon.dbfinder.infrastructure.enums.RuleMode
import kotlinx.uuid.exposed.KotlinxUUIDTable

object Rules : KotlinxUUIDTable("rules") {
    val name = varchar("name", 255).default("")
    val description = text("description").default("")
    val mode = enumerationByName("mode", 255, RuleMode::class)
    val priority = integer("priority").default(0)
    val prompt = varchar("prompt", 255).default("")
    val isArchived = bool("is_archived").default(false)
    val contentSrc = varchar("content_src", 255).default("")
    val version = integer("version").default(1)
    val homebrewID = varchar("homebrew_id", 255).nullable().default(null)
}
