package io.sengokudaikon.isn.app.domain.user

import kotlinx.serialization.Serializable

@Serializable
enum class UserRole {
    USER,
    GAMEMASTER,
    ADMIN,
    OWNER,
}
