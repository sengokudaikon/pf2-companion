package io.sengokudaikon.isn.app.persistence.user.entity

import io.sengokudaikon.isn.app.domain.user.model.UserRole
import kotlinx.uuid.exposed.KotlinxUUIDTable
import org.jetbrains.exposed.sql.javatime.datetime

object Users : KotlinxUUIDTable(name = "users") {
    val uid = varchar("uid", 255).uniqueIndex()
    val email = varchar("email", 255).uniqueIndex()
    val username = varchar("user_name", 255)
    val password = varchar("password", 255)
    val role = enumeration("role", UserRole::class)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
