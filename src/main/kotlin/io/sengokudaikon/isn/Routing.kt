package io.sengokudaikon.isn

import com.github.dimitark.ktor.routing.ktorRoutingAnnotationConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
    }

    ktorRoutingAnnotationConfig()
}
