package io.sengokudaikon.isn.app.operations.user.command

import io.ktor.resources.*
import io.sengokudaikon.isn.app.domain.user.UserRole
import io.sengokudaikon.isn.infrastructure.operations.Command
import io.sengokudaikon.isn.infrastructure.validation.constraints.ValidEmail
import io.sengokudaikon.isn.infrastructure.validation.constraints.ValidUsername

interface UserCommand {
    @Resource("/api/auth/register")
    data class Create(
        @ValidEmail val email: String,
        @ValidUsername val username: String,
        val name: String,
        val uid: String,
        val role: UserRole = UserRole.USER,
    ) : Command

    @Resource("/api/auth/signin")
    data class SignIn(
        val uid: String,
    ) : Command

    @Resource("/api/user/update")
    data class Update(
        val id: String,
        val role: UserRole?,
        val email: String?,
        val username: String?,
    ) : Command

    @Resource("/api/user/delete")
    data class Delete(
        val id: String,
    ) : Command
}
