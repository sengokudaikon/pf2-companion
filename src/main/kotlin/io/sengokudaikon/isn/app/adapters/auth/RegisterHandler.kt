package io.sengokudaikon.isn.app.adapters.auth

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.infrastructure.adapters.CommandHandler
import io.sengokudaikon.isn.infrastructure.adapters.uid
import io.sengokudaikon.isn.infrastructure.errors.AuthException
import io.sengokudaikon.isn.infrastructure.errors.UserException
import io.sengokudaikon.isn.infrastructure.validation.validate
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [CommandHandler::class])
class RegisterHandler : CommandHandler() {
    private val authUseCase: AuthPort by inject()
    override suspend fun execute(call: ApplicationCall) {
        val uid = call.uid()
        val command = call.receive<UserCommand.Auth.Register>()
        try {
            validate(command)
        } catch (e: IllegalArgumentException) {
            call.respond(AuthException.Invalid(e.message ?: "Error during registration", e))
        }
        require(
            !authUseCase.checkIfMailExists(command.email).getOrThrow()
                    && !authUseCase.checkIfUsernameExists(command.username).getOrThrow()
                    && !authUseCase.checkIfUidExists(uid).getOrThrow()
        ) {
            throw UserException.AlreadyExists()
        }
        val result = authUseCase.register(command, uid)
        result.fold(
            onFailure = { error ->
                call.respond(AuthException.Invalid(error.localizedMessage ?: "Error during registration", error))
            },
            onSuccess = {
                call.respond(
                    mapOf(
                        "uid" to it,
                    ),
                )
            },
        )
    }
}
