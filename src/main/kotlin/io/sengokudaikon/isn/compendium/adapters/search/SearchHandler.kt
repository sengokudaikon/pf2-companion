package io.sengokudaikon.isn.compendium.adapters.search

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.compendium.ports.world.SearchPort
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class SearchHandler : QueryHandler() {
    override val useCase: SearchPort by inject()
    override suspend fun handle(call: ApplicationCall) {
        val query = call.parameters["query"] ?: ""
        var filters = call.parameters["filter"]
        val queryObject = createQuery(query, filters)
        val result = useCase.execute(queryObject)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it },
            ),
        )
    }

    fun createQuery(query: String, filters: String?): SearchQuery {
        return SearchQuery(query, filters)
    }
}
