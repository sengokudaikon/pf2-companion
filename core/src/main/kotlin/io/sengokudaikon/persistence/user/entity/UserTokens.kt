package io.sengokudaikon.persistence.user.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object UserTokens : KotlinxUUIDTable(name = "user_tokens") {
    val userId = varchar("user_id", 255).uniqueIndex()
    val token = varchar("token", 255).uniqueIndex()
}
