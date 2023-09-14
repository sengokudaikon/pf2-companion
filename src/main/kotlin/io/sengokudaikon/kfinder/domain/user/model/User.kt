package io.sengokudaikon.kfinder.domain.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: String, val email: String, val username: String, val role: UserRole)
