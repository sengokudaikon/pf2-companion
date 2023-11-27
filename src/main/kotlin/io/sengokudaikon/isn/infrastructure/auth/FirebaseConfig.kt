package io.sengokudaikon.isn.infrastructure.auth

import com.google.firebase.auth.FirebaseToken
import io.ktor.http.auth.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.sengokudaikon.isn.infrastructure.logger

class FirebaseConfig(name: String?) : AuthenticationProvider.Config(name) {
    internal var authHeader: (ApplicationCall) -> HttpAuthHeader? =
        { call -> call.request.parseAuthorizationHeaderOrNull() }

    var firebaseAuthenticationFunction: AuthenticationFunction<FirebaseToken> = {
        throw NotImplementedError(
            "Firebase user validate function is not specified, use firebase { validate { ... } } to fix this",
        )
    }

    fun validate(validate: suspend ApplicationCall.(FirebaseToken) -> FirebasePrincipal?) {
        firebaseAuthenticationFunction = validate
    }
}

fun AuthenticationConfig.firebase(
    name: String? = "firebase",
    configure: FirebaseConfig.() -> Unit,
) {
    val provider = FirebaseAuthProvider(FirebaseConfig(name).apply(configure))
    register(provider)
}

fun ApplicationRequest.parseAuthorizationHeaderOrNull(): HttpAuthHeader? = try {
    parseAuthorizationHeader()
} catch (ex: IllegalArgumentException) {
    logger.warn("Failed to parse authorization header", ex)
    null
}
