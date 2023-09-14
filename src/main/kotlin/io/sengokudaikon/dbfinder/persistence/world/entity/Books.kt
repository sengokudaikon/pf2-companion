package io.sengokudaikon.dbfinder.persistence.world.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Books : KotlinxUUIDTable("book") {
    val name = varchar("name", 50)
    val codeName = varchar("codeName", 10)
    val description = text("description")
    val url = varchar("url", 100)
    val code = varchar("code", 10)
}
