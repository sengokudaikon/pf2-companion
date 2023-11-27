package io.sengokudaikon.isn.compendium.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Conditions : KotlinxUUIDTable("conditions") {
    val name = varchar("name", 50)
    val description = text("description")
    val hasValue = bool("hasValue").default(false)
    val value = integer("value").nullable()
    val hasDuration = bool("hasDuration").default(false)
    val duration = integer("duration").nullable()
}
