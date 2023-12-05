package io.sengokudaikon.isn.app.adapters.auth

import com.github.dimitark.ktorannotations.annotations.Post
import com.github.dimitark.ktorannotations.annotations.RouteController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.infrastructure.validation.validate
import io.swagger.v3.oas.annotations.tags.Tag

@RouteController
@Tag(name = "Auth")
class AuthenticationAdapter(
    private val authUseCase: AuthPort,
) : io.sengokudaikon.isn.app.adapters.Adapter() {
    @Post("/api/register")
    suspend fun register(call: ApplicationCall) {
        val uid = uid(call)
        val command = call.receive<UserCommand.Create>()
        try {
            validate(command)
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Error during registration")
        }

        val userExists = authUseCase.checkIfMailExists(command.email) ||
            authUseCase.checkIfExists(uid)
        if (userExists) {
            call.respond(HttpStatusCode.BadRequest, "User already exists")
        }
        val result = authUseCase.register(command)
        return result.fold(
            onFailure = { error ->
                call.respond(HttpStatusCode.BadRequest, error.localizedMessage ?: "Error during registration")
            },
            onSuccess = {
                call.respond(
                    HttpStatusCode.Created,
                    message = mapOf(
                        "uid" to result.getOrNull(),
                    ),
                )
            },
        )
    }

    @Post("/api/usecases")
    suspend fun authenticate(call: ApplicationCall) {
        val uid = uid(call)
        val userExists = authUseCase.checkIfExists(uid)
        if (!userExists) {
            call.respond(HttpStatusCode.BadRequest, "User doesn't exist")
        }
        val result = authUseCase.authenticate(UserCommand.SignIn(uid))
        return result.fold(
            onFailure = { error ->
                call.respond(HttpStatusCode.BadRequest, error.message ?: "Error during registration")
            },
            onSuccess = { user ->
                call.respond(
                    HttpStatusCode.OK,
                    message = user,
                )
            },
        )
    }
}
