package io.sengokudaikon.isn.compendium.adapters.character

import com.github.dimitark.ktorannotations.annotations.Get
import com.github.dimitark.ktorannotations.annotations.RouteController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.adapters.Adapter
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.GetAncestryPort
import io.sengokudaikon.isn.compendium.ports.character.GetByNameAncestryPort
import io.sengokudaikon.isn.compendium.ports.character.ListAncestryPort
import io.swagger.v3.oas.annotations.tags.Tag
import org.koin.core.annotation.Single

@RouteController
@Single(binds = [Adapter::class])
@Tag(name = "Ancestry")
@Suppress("unused")
class AncestryAdapter(
    private val list: ListAncestryPort,
    private val get: GetAncestryPort,
    private val getByName: GetByNameAncestryPort,
) : Adapter() {
    @Get("/api/ancestry/list/{page}/{size}")
    suspend fun getAncestryList(call: ApplicationCall) {
        val page = call.parameters["page"]?.toInt() ?: 1
        val size = call.parameters["size"]?.toInt() ?: 10
        val query = AncestryQuery.FindAll(page, size)
        val result = list.execute(query)
        result.fold(
            onFailure = { call.respond(it) },
            onSuccess = { call.respond(it.map { model -> model.toResponse() }) },
        )
    }

    @Get("/api/ancestry/{name}")
    suspend fun getAncestry(call: ApplicationCall) {
        val name = call.parameters["name"] ?: throw IllegalArgumentException("name is required")
        val query = AncestryQuery.FindByName(name)
        getByName.execute(query).fold(
            { call.respond(it) },
            { call.respond(it) },
        )
    }
}
