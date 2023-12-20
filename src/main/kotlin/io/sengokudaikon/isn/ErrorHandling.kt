package io.sengokudaikon.isn

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.handleErrors() {
    install(StatusPages) {
        exception<InternalError> { call, cause ->
            this@handleErrors.log.error(cause.localizedMessage)
            call.respond(
                HttpStatusCode.InternalServerError,
                "500: ${cause.localizedMessage}" +
                    "\n${cause.stackTraceToString()}",
            )
        }
        exception<Throwable> { call, cause ->
            this@handleErrors.log.error(cause.localizedMessage)
            call.respond(
                HttpStatusCode.InternalServerError,
                "500: ${cause.localizedMessage}" + "\n${cause.stackTraceToString()}",
            )
        }
        exception<IllegalArgumentException> { call, cause ->
            this@handleErrors.log.error(cause.localizedMessage)
            call.respond(
                HttpStatusCode.BadRequest,
                "400: ${cause.localizedMessage}" + "\n${cause.stackTraceToString()}",
            )
        }
        exception<BadRequestException> { call, cause ->
            this@handleErrors.log.error(cause.localizedMessage)
            call.respond(
                HttpStatusCode.BadRequest,
                "400: ${cause.localizedMessage}" + "\n${cause.stackTraceToString()}",
            )
        }
        exception<NotFoundException> { call, cause ->
            this@handleErrors.log.error(cause.localizedMessage)
            call.respond(
                HttpStatusCode.NotFound,
                "404: ${cause.localizedMessage}" + "\n${cause.stackTraceToString()}",
            )
        }
    }
}
