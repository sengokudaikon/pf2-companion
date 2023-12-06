package io.sengokudaikon.isn.compendium.adapters.character.ancestry

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.QueryHandler
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.GetAncestryPort
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryIdHandler : QueryHandler() {
    override val useCase: GetAncestryPort by inject()

    override suspend fun handle(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing id")
        val query = AncestryQuery.FindById(id)
        call.respond(
            useCase.execute(query).fold(
                onFailure = { throw it },
                onSuccess = { it.toResponse() },
            ),
        )
    }
}
