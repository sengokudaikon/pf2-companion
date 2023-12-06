package io.sengokudaikon.isn.infrastructure.errors

import io.ktor.http.*

sealed class UserException(code: HttpStatusCode, message: String, previous: Throwable? = null) :
    Throwable(code.toString() + message, previous) {
    class Invalid(message: String) : UserException(HttpStatusCode.BadRequest, message)
    class NotFound(message: String = "User not found") : UserException(HttpStatusCode.NotFound, message)
    class AlreadyExists(message: String = "User already exists") : UserException(HttpStatusCode.BadRequest, message)
    class Unauthorized(message: String) : UserException(HttpStatusCode.Unauthorized, message)
    class Forbidden(message: String) : UserException(HttpStatusCode.Forbidden, message)
    class BadRequest(message: String) : UserException(HttpStatusCode.BadRequest, message)
}
