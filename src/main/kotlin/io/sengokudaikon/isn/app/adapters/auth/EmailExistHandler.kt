package io.sengokudaikon.isn.app.adapters.auth

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.CommandHandler
import io.sengokudaikon.isn.app.ports.auth.AuthPort
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [CommandHandler::class])
class EmailExistHandler : CommandHandler() {
    private val useCase: AuthPort by inject()
    override suspend fun execute(call: ApplicationCall) {
        val email = call.parameters["email"]!!
        val result = useCase.checkIfMailExists(email)
        call.respondText(
            result.toString(),
        )
    }
}
