package io.sengokudaikon.isn.chargen.adapters.character.read

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.chargen.ports.ByNameUserCharacterPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import io.sengokudaikon.isn.infrastructure.adapters.uid
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class CharacterByNameUserHandler :
    ByNameHandler<CharacterModel, CharacterQuery.ByNameAndUser, ByNameUserCharacterPort>() {
    override val useCase: ByNameUserCharacterPort by inject()
    override fun createQuery(name: String, id: String?): CharacterQuery.ByNameAndUser {
        return CharacterQuery.ByNameAndUser(name).apply { this.userId = id!! }
    }

    override suspend fun handle(call: ApplicationCall) {
        val userId = call.uid()
        val name = call.parameters["name"] ?: throw IllegalArgumentException("Missing name")
        val query = createQuery(name, userId)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it },
            ),
        )
    }
}