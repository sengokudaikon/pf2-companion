package io.sengokudaikon.isn.app.operations.user.command

import io.sengokudaikon.isn.app.domain.user.UserRole
import io.sengokudaikon.isn.infrastructure.validation.constraints.ValidEmail
import io.sengokudaikon.isn.infrastructure.validation.constraints.ValidUsername
import kotlinx.serialization.Serializable

interface UserCommand {
    @Serializable
    data class Create(
        @ValidEmail val email: String,
        @ValidUsername val username: String,
        val name: String,
        val uid: String,
        val role: UserRole = UserRole.USER,
    ) : UserCommand

    @Serializable
    data class SignIn(
        val uid: String,
    ) : UserCommand

    @Serializable
    data class Update(
        val id: String,
        val role: UserRole?,
        val email: String?,
        val username: String?,
    ) : UserCommand

    @Serializable
    data class Delete(
        val id: String,
    ) : UserCommand
}
