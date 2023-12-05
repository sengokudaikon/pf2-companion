package io.sengokudaikon.isn.app.domain.user

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import kotlinx.serialization.Serializable
import java.time.LocalDateTime.now

@Serializable
data class User(
    val id: String,
    val uid: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val createdAt: LocalDateTime = now().toKotlinLocalDateTime(),
    val updatedAt: LocalDateTime = now().toKotlinLocalDateTime(),
)
