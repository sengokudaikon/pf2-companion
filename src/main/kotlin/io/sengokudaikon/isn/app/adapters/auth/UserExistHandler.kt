package io.sengokudaikon.isn.app.adapters.auth

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import io.sengokudaikon.isn.infrastructure.adapters.CommandHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [CommandHandler::class])
class UserExistHandler : CommandHandler() {
    private val authUseCase: AuthPort by inject()
    override suspend fun execute(call: ApplicationCall) {
        val userId = call.parameters["username"]!!
        val result = authUseCase.findUserByUsername(userId)
        call.respondText(
            result.toString(),
        )
    }
}
