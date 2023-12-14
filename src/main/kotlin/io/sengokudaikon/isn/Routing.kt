package io.sengokudaikon.isn

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.sengokudaikon.isn.app.adapters.auth.EmailExistHandler
import io.sengokudaikon.isn.app.adapters.auth.RegisterHandler
import io.sengokudaikon.isn.app.adapters.auth.UserExistHandler
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.app.operations.user.query.EmailExists
import io.sengokudaikon.isn.app.operations.user.query.UserExists
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryIdHandler
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryListHandler
import io.sengokudaikon.isn.compendium.adapters.character.ancestry.AncestryNameHandler
import io.sengokudaikon.isn.compendium.adapters.world.SearchHandler
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.operations.search.query.SearchQuery
import io.ktor.server.resources.get as getResource
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

        getResource<AncestryQuery.All> { AncestryListHandler().handle(call) }
        getResource<AncestryQuery.ById> { AncestryIdHandler().handle(call) }
        getResource<AncestryQuery.ByName> { AncestryNameHandler().handle(call) }
        postResource<SearchQuery> { SearchHandler().execute(call) }
        postResource<UserCommand.Create> { RegisterHandler().execute(call) }
        getResource<EmailExists> {
            EmailExistHandler().execute(call)
        }
        options<EmailExists> { call.respondNullable(HttpStatusCode.OK) }
        getResource<UserExists> {
            UserExistHandler().execute(call)
        }
        options<UserExists> { call.respondNullable(HttpStatusCode.OK) }
    }
}
