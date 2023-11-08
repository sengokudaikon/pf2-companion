package io.sengokudaikon.dbfinder.adapters.world

import com.github.dimitark.ktorannotations.annotations.Get
import com.github.dimitark.ktorannotations.annotations.RouteController
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.dbfinder.operations.world.trait.query.TraitQuery
import io.sengokudaikon.dbfinder.ports.world.TraitPort
import io.swagger.v3.oas.annotations.tags.Tag
import org.koin.core.annotation.Single

@RouteController
@Single
@Tag(name = "Trait")
class TraitAdapter(
    private val traitUseCase: TraitPort,
) {
    @Get("/api/trait/list/{page}/{size}")
    suspend fun getTraitList(call: ApplicationCall) {
        val page = call.parameters["page"]?.toInt() ?: 1
        val size = call.parameters["size"]?.toInt() ?: 10
        val query = TraitQuery.FindAll(page, size)
        traitUseCase.list(query).fold(
            { call.respond(it) },
            { call.respond(it) },
        )
    }

    @Get("/api/trait/{name}")
    suspend fun getTrait(call: ApplicationCall) {
        val name = call.parameters["name"] ?: throw IllegalArgumentException("name is required")
        val query = TraitQuery.FindByName(name)
        traitUseCase.get(query).fold(
            { call.respond(it) },
            { call.respond(it) },
        )
    }
}
