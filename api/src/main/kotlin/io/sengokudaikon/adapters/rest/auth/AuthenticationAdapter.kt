package io.sengokudaikon.adapters.rest.auth

import com.github.dimitark.ktorannotations.annotations.Post
import com.github.dimitark.ktorannotations.annotations.RouteController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.sengokudaikon.adapters.rest.Adapter
import io.sengokudaikon.infrastructure.validation.validate
import io.sengokudaikon.operations.user.command.UserCommand
import io.sengokudaikon.ports.auth.AuthPort
import io.swagger.v3.oas.annotations.tags.Tag

@RouteController
@Tag(name = "Auth")
class AuthenticationAdapter(
    private val authUseCase: AuthPort,
): Adapter() {
    @Post("/api/register")
    suspend fun register(call: ApplicationCall) {
        val command = call.receive<UserCommand.Create>()
        try {
            validate(command)
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Error during registration")
        }

        val userExists = authUseCase.checkIfMailExists(command.email)
        if (userExists) {
            call.respond(HttpStatusCode.BadRequest, "User already exists")
        }
        val result = authUseCase.register(command)
        return result.fold(
            { error -> call.respond(HttpStatusCode.BadRequest, error.message ?: "Error during registration") },
            {
                call.respond(
                    HttpStatusCode.Created,
                    message = mapOf(
                        "uid" to result.getOrNull(),
                    ),
                )
            },
        )
    }
    @Post("/api/auth")
    suspend fun authenticate(call: ApplicationCall) {
        val uid = call.request.queryParameters["uid"]
        val command = call.receive<UserCommand.SignIn>()
        try {
            validate(command)
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Error during registration")
        }

        val userExists = authUseCase.checkIfExists(uid)
                || command.email?.let { authUseCase.checkIfMailExists(it) } == true
        if (!userExists) {
            call.respond(HttpStatusCode.BadRequest, "User doesn't exist")
        }
        val result = authUseCase.authenticate(uid, command)
        return result.fold(
            { error -> call.respond(HttpStatusCode.BadRequest, error.message ?: "Error during registration") },
            { user -> call.respond(
                    HttpStatusCode.OK,
                    message = user,
                )
            },
        )
    }
}
