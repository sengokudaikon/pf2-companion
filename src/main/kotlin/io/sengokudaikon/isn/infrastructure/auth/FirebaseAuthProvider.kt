package io.sengokudaikon.isn.infrastructure.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import io.ktor.http.auth.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.infrastructure.logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseAuthProvider(config: FirebaseConfig) : AuthenticationProvider(config) {
    val authHeader: (ApplicationCall) -> HttpAuthHeader? = config.authHeader
    private val authFunction = config.firebaseAuthenticationFunction
    private val firebaseAuthKey: String = "FirebaseAuth"
    override suspend fun onAuthenticate(context: AuthenticationContext) {
        val token = authHeader(context.call)

        if (token == null) {
            context.challenge(
                firebaseAuthKey,
                AuthenticationFailedCause.InvalidCredentials,
            ) { challengeFunc, call ->
                challengeFunc.complete()
                call.respond(
                    UnauthorizedResponse(
                        HttpAuthHeader.bearerAuthChallenge(
                            scheme = "Bearer",
                            realm = "firebase",
                        ),
                    ),
                )
            }
            return
        }

        try {
            val principal = verifyFirebaseIdToken(context.call, token, authFunction)

            if (principal != null) {
                context.principal(principal)
            }
        } catch (cause: Throwable) {
            val message = cause.message ?: cause.javaClass.simpleName
            context.error(firebaseAuthKey, AuthenticationFailedCause.Error(message))
        }
    }

    private suspend fun verifyFirebaseIdToken(
        call: ApplicationCall,
        authHeader: HttpAuthHeader,
        tokenData: suspend ApplicationCall.(FirebaseToken) -> Principal?,
    ): Principal? = withContext(Dispatchers.IO) {
        if (authHeader.authScheme == "Bearer" && authHeader is HttpAuthHeader.Single) {
            return@withContext try {
                FirebaseAuth.getInstance().verifyIdToken(authHeader.blob)
            } catch (ex: Exception) {
                logger.debug("Failed to verify firebase token", ex)
                null
            }
        } else {
            null
        }
    }?.let { tokenData(call, it) }
}
