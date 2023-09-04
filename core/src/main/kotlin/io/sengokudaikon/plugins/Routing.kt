package io.sengokudaikon.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.swagger.codegen.v3.generators.html.StaticHtmlCodegen
import java.io.File

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        File("openapi/documentation.yaml").takeIf { it.exists() }?.let {
            swaggerUI(path = "/api/docs", swaggerFile = "openapi/documentation.yaml") {
                version = "4.15.5"
                configLoaders
            }
            openAPI(path = "/api/openapi", swaggerFile = "openapi/documentation.yaml") {
                codegen = StaticHtmlCodegen()
            }
        } ?: run {
            error("OpenAPI configuration file is missing. Please check its existence at the defined location.")
        }

        get("/api/health") {
            call.respond(HttpStatusCode.OK, "Healthy")
        }
    }
}
