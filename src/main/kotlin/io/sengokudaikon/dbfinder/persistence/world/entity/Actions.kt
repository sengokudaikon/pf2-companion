package io.sengokudaikon.dbfinder.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Actions : KotlinxUUIDTable("actions") {
    val name = varchar("name", 255)
    val image = varchar("image", 255)
    val description = text("description")
    val actionType = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.ActionTypes>("action_type", 50)
    val actionNumber = integer("actions").nullable().default(null)
    val category = enumerationByName<io.sengokudaikon.dbfinder.infrastructure.enums.ActionCategories>("category", 50)
    val frequency = varchar("frequency", 255).default("")
    val requirements = text("requirements").default("")
    val contentSrc = varchar("contentSrc", 255).default("")
    val trigger = text("trigger").default("")
}
