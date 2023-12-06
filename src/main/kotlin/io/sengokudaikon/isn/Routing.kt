package io.sengokudaikon.isn

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.sengokudaikon.isn.app.adapters.CommandHandler
import io.sengokudaikon.isn.app.adapters.QueryHandler
import io.sengokudaikon.isn.app.adapters.auth.AuthHandler
import io.sengokudaikon.isn.app.adapters.auth.RegisterHandler
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryListHandler
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.SearchHandler
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.sengokudaikon.isn.infrastructure.operations.Command
import io.sengokudaikon.isn.infrastructure.operations.Query
import org.koin.ktor.ext.inject
import io.ktor.server.resources.post as postResource
fun Application.configureRouting() {
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello World!", contentType = ContentType.Text.Plain)
        }
        swaggerUI(path = "/api/docs", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
            configLoaders
        }

        get("/api/health") {
            call.respond(HttpStatusCode.OK, "Healthy")
        }

        query<AncestryQuery.FindAll, AncestryListHandler>()
        query<AncestryQuery.FindById, AncestryIdHandler>()
        query<AncestryQuery.FindByName, AncestryNameHandler>()
        query<SearchQuery, SearchHandler>()
        command<UserCommand.Create, RegisterHandler>()
        command<UserCommand.SignIn, AuthHandler>()
    }
}

inline fun <reified Q : Query, reified H : QueryHandler> Routing.query() {
    val handler: H by inject()
    get<Q> {
        handler.handle(call)
    }
}
inline fun <reified C : Command, reified H : CommandHandler> Routing.command() {
    val handler: H by inject()
    postResource<C> {
        handler.execute(call)
    }
}
