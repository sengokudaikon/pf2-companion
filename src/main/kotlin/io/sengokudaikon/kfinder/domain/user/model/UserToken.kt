package io.sengokudaikon.kfinder.domain.user.model

import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    val userId: String,
    val token: String,
)
