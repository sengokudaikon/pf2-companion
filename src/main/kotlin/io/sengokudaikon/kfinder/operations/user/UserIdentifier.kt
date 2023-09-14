package io.sengokudaikon.kfinder.operations.user

import io.sengokudaikon.infrastructure.validation.constraints.ValidEmail
import io.sengokudaikon.infrastructure.validation.constraints.ValidUsername
import kotlinx.serialization.Serializable

@Serializable
sealed class UserIdentifier {
    @Serializable
    data class Email(@ValidEmail val email: String) : UserIdentifier()

    @Serializable
    data class Username(@ValidUsername val username: String) : UserIdentifier()

    @Serializable
    data class Uid(val uid: String) : UserIdentifier()
}
