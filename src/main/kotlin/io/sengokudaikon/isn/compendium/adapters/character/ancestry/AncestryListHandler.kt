package io.sengokudaikon.isn.compendium.adapters.character.ancestry

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.QueryHandler
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ListAncestryPort
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryListHandler : QueryHandler() {
    override val useCase: ListAncestryPort by inject()

    override suspend fun handle(call: ApplicationCall) {
        val page = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException("Missing page")
        val size = call.parameters["limit"]?.toInt() ?: throw IllegalArgumentException("Missing size")
        val query = AncestryQuery.FindAll(page, size)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.map { model -> model.toResponse() } },
            ),
        )
    }
}
