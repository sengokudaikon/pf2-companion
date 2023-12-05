package io.sengokudaikon.isn.compendium.adapters.world

import com.github.dimitark.ktorannotations.annotations.Get
import com.github.dimitark.ktorannotations.annotations.RouteController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.Adapter
import io.sengokudaikon.isn.compendium.ports.world.SearchPort
import io.swagger.v3.oas.annotations.tags.Tag
import org.koin.core.annotation.Single

@RouteController
@Single(binds = [Adapter::class])
@Tag(name = "Search")
class LookupAdapter(
    private val search: SearchPort,
) : Adapter() {
    @Get("/api/search/{query}")
    suspend fun search(call: ApplicationCall) {
        val query = call.parameters["query"] ?: throw IllegalArgumentException("query is required")
        val result = search.execute(SearchPort.Query(query))
        result.fold(
            onFailure = { call.respond(it) },
            onSuccess = { call.respond(it) },
        )
    }
}
