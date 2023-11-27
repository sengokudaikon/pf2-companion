package io.sengokudaikon.isn.app.domain.user.model

import kotlinx.serialization.Serializable

@Serializable
enum class UserRole {
    USER,
    GAMEMASTER,
    ADMIN,
    OWNER,
}
