package io.sengokudaikon.isn.app.operations.user.command

import io.ktor.resources.*
import io.sengokudaikon.isn.app.domain.user.UserRole
import io.sengokudaikon.isn.infrastructure.operations.Command
import io.sengokudaikon.isn.infrastructure.validation.constraints.ValidEmail
import io.sengokudaikon.isn.infrastructure.validation.constraints.ValidUsername

interface UserCommand {
    @Resource("/api/auth")
    class Auth : Command, UserCommand {
        data class Register(
            @ValidEmail val email: String,
            @ValidUsername val username: String,
        ): UserCommand {
            var role = UserRole.USER
        }
        @Resource("update")
        data class Update(
            val id: String,
            val role: UserRole?,
            val email: String?,
            val username: String?,
        ) : UserCommand

        @Resource("delete")
        data class Delete(
            val id: String,
        ) : Command, UserCommand
    }
}
