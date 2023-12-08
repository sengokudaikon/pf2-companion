package io.sengokudaikon.isn.compendium.adapters.world

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.QueryHandler
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.compendium.ports.world.SearchPort
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SearchHandler : QueryHandler() {
    override val useCase: SearchPort by inject()
    override suspend fun handle(call: ApplicationCall) {
        val query = call.parameters["query"]!!
        val result = useCase.execute(SearchQuery(query))
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it },
            ),
        )
    }
}
