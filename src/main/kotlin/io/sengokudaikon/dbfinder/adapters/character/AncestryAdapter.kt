package io.sengokudaikon.dbfinder.adapters.character

import com.github.dimitark.ktorannotations.annotations.Get
import com.github.dimitark.ktorannotations.annotations.RouteController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.dbfinder.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.dbfinder.ports.character.AncestryPort
import io.sengokudaikon.kfinder.adapters.Adapter
import io.swagger.v3.oas.annotations.tags.Tag
import org.koin.core.annotation.Single

@RouteController
@Single(binds = [Adapter::class])
@Tag(name = "Ancestry")
class AncestryAdapter(
    private val ancestryUseCase: AncestryPort,
) : Adapter() {
    @Get("/api/ancestry/list/{page}/{size}")
    suspend fun getAncestryList(call: ApplicationCall) {
        val page = call.parameters["page"]?.toInt() ?: 1
        val size = call.parameters["size"]?.toInt() ?: 10
        val query = AncestryQuery.FindAll(page, size)
        ancestryUseCase.list(query).fold(
            { call.respond(it) },
            { call.respond(it) },
        )
    }

    @Get("/api/ancestry/{name}")
    suspend fun getAncestry(call: ApplicationCall) {
        val name = call.parameters["name"] ?: throw IllegalArgumentException("name is required")
        val query = AncestryQuery.FindByName(name)
        ancestryUseCase.get(query).fold(
            { call.respond(it) },
            { call.respond(it) },
        )
    }
}
