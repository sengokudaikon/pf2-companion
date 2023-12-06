package io.sengokudaikon.isn.app.adapters.auth

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.CommandHandler
import io.sengokudaikon.isn.app.adapters.uid
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.infrastructure.errors.AuthException
import io.sengokudaikon.isn.infrastructure.errors.UserException
import org.koin.core.component.inject

@Resource("/api/auth")
class AuthHandler : CommandHandler() {
    private val authUseCase: AuthPort by inject()
    override suspend fun execute(call: ApplicationCall) {
        val uid = call.uid()
        val userExists = authUseCase.checkIfExists(uid)
        if (!userExists) {
            throw UserException.NotFound()
        }
        val result = authUseCase.authenticate(UserCommand.SignIn(uid))
        result.fold(
            onFailure = { call.respond(AuthException.Invalid(it.localizedMessage ?: "Error during registration", it)) },
            onSuccess = { call.respond(it) },
        )
    }
}
