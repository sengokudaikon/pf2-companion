package io.sengokudaikon.isn.chargen.adapters.character.write

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.sengokudaikon.isn.app.domain.user.UserRole
import io.sengokudaikon.isn.chargen.operations.character.command.CharacterCommand
import io.sengokudaikon.isn.chargen.ports.CharacterCreationPort
import io.sengokudaikon.isn.infrastructure.adapters.CommandHandler
import org.koin.core.component.inject

class CharacterCreationHandler : CommandHandler() {
    private val useCase: CharacterCreationPort by inject()
    override suspend fun execute(call: ApplicationCall) {
        call.authorize(listOf(UserRole.USER)) {
            val command = call.receive<CharacterCommand.Create>()
            useCase.create(command)
        }
    }
}
