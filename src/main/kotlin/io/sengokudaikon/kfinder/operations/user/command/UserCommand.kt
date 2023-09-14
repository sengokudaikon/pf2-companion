package io.sengokudaikon.kfinder.operations.user.command

import io.sengokudaikon.kfinder.domain.user.model.UserRole
import io.sengokudaikon.kfinder.infrastructure.validation.constraints.ValidEmail
import io.sengokudaikon.kfinder.infrastructure.validation.constraints.ValidPassword
import io.sengokudaikon.kfinder.infrastructure.validation.constraints.ValidUsername
import io.sengokudaikon.kfinder.operations.user.UserIdentifier
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

interface UserCommand {
    @Serializable
    data class Create(
        @ValidEmail val email: String,
        @ValidUsername val username: String,
        @ValidPassword val password: String,
    ) : UserCommand

    @Serializable
    data class SignIn(
        val identifier: UserIdentifier,
        @ValidPassword val password: String,
    ) : UserCommand

    @Serializable
    data class Update(
        val id: UUID,
        val role: UserRole?,
        val email: String?,
        val username: String?,
    ) : UserCommand

    @Serializable
    data class Delete(
        val id: UUID,
    ) : UserCommand
}
