package io.sengokudaikon.isn.compendium.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Languages : KotlinxUUIDTable("languages") {
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 1000).nullable().default(null)
}
