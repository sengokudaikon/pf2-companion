package io.sengokudaikon.isn.chargen.adapters.character.read

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.chargen.ports.ByIdUserCharacterPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import io.sengokudaikon.isn.infrastructure.adapters.uid
import io.sengokudaikon.isn.infrastructure.domain.Model
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class CharacterByIdUserHandler : ByIdHandler<CharacterModel, CharacterQuery.ByIdAndUser, ByIdUserCharacterPort>() {
    override val useCase: ByIdUserCharacterPort by inject()
    override suspend fun handle(call: ApplicationCall) {
        val userId = call.uid()
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing id")
        val query = createQuery(id, null).apply { this.userId = userId }
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.toResponse<Model>() },
            ),
        )
    }

    override fun createQuery(id: String, secondaryId: String?): CharacterQuery.ByIdAndUser {
        return CharacterQuery.ByIdAndUser(id)
    }
}
