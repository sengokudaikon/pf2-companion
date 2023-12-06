package io.sengokudaikon.isn.compendium.adapters.character.ancestry // ktlint-disable filename

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.QueryHandler
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.GetByNameAncestryPort
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryNameHandler : QueryHandler() {
    override val useCase: GetByNameAncestryPort by inject()

    override suspend fun handle(call: ApplicationCall) {
        val name = call.parameters["name"] ?: throw IllegalArgumentException("Missing name")
        val query = AncestryQuery.FindByName(name)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.toResponse() },
            ),
        )
    }
}
