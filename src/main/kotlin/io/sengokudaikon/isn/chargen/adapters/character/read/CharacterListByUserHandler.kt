package io.sengokudaikon.isn.chargen.adapters.character.read

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.chargen.ports.ListUserCharactersPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import io.sengokudaikon.isn.infrastructure.adapters.uid
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class CharacterListByUserHandler:
    ListHandler<List<CharacterModel>, CharacterQuery.ListByUser, ListUserCharactersPort>() {
    override val useCase: ListUserCharactersPort by inject()
    override suspend fun handle(call: ApplicationCall) {
        val uid = call.uid()
        val page = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException("Missing page")
        val size = call.parameters["size"]?.toInt() ?: throw IllegalArgumentException("Missing size")
        val query = createQuery(page, size, uid)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it },
            ),
        )
    }

    override fun createQuery(page: Int, size: Int, id: String?): CharacterQuery.ListByUser {
        return CharacterQuery.ListByUser(page, size).apply { this.userId = id!! }
    }
}