package io.sengokudaikon.isn.compendium.adapters.world

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.compendium.ports.world.SearchPort
import io.sengokudaikon.isn.infrastructure.adapters.CommandHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [CommandHandler::class])
class SearchHandler : CommandHandler() {
    val useCase: SearchPort by inject()
    override suspend fun execute(call: ApplicationCall) {
        val query = call.receive<SearchQuery>()
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it },
            ),
        )
    }
}
