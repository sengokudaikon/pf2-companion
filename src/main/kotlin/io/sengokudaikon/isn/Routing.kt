package io.sengokudaikon.isn

import com.github.dimitark.ktor.routing.ktorRoutingAnnotationConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.swagger.codegen.v3.generators.kotlin.KotlinServerCodegen

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
        openAPI(path = "/api/openapi", swaggerFile = "openapi/documentation.yaml") {
            codegen = KotlinServerCodegen()
        }

        get("/api/health") {
            call.respond(HttpStatusCode.OK, "Healthy")
        }
    }

    ktorRoutingAnnotationConfig()
}
