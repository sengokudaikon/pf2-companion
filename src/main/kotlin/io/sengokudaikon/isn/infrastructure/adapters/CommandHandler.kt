package io.sengokudaikon.isn.infrastructure.adapters

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.sengokudaikon.isn.app.domain.user.UserRole
import io.sengokudaikon.isn.infrastructure.AuthorizationService
import io.sengokudaikon.isn.infrastructure.auth.FirebasePrincipal
import io.sengokudaikon.isn.infrastructure.errors.UserException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class CommandHandler : KoinComponent {
    abstract suspend fun execute(call: ApplicationCall)
    private val authorization: AuthorizationService by inject()
    protected suspend fun <T> ApplicationCall.authorize(
        requiredRoles: List<UserRole>,
        block: suspend () -> T,
    ) {
        return authorization.authorize(this, requiredRoles, block)
    }

    protected suspend fun ApplicationCall.fromUid(): String {
        return authorization.getUserId(this)
    }
}
fun ApplicationCall.uid(): String {
    return this.principal<FirebasePrincipal>()?.uid ?: throw UserException.Unauthorized("Unauthorized user")
}
