package io.sengokudaikon.isn.infrastructure.errors

import io.ktor.http.*

sealed class AuthException(code: HttpStatusCode, message: String, previous: Throwable? = null) :
    Throwable("$code: $message", previous) {
    class Invalid(message: String, previous: Throwable?) : AuthException(HttpStatusCode.BadRequest, message, previous)
}
