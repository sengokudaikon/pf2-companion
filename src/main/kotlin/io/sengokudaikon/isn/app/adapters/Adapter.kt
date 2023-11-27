package io.sengokudaikon.isn.app.adapters

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.sengokudaikon.isn.app.domain.user.model.UserRole
import io.sengokudaikon.isn.infrastructure.AuthorizationService
import io.sengokudaikon.isn.infrastructure.auth.FirebasePrincipal
import io.sengokudaikon.isn.infrastructure.errors.UserException
import kotlinx.uuid.UUID
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Adapter : KoinComponent {
    private val authorization: AuthorizationService by inject()
    protected suspend fun <T> authorize(
        call: ApplicationCall,
        requiredRoles: List<UserRole>,
        block: suspend () -> T,
    ) {
        return authorization.authorize(call, requiredRoles, block)
    }

    protected suspend fun fromUid(call: ApplicationCall): UUID {
        return authorization.getUserId(call)
    }

    fun uid(call: ApplicationCall): String {
        return call.principal<FirebasePrincipal>()?.uid ?: throw UserException.Unauthorized("Unauthorized user")
    }
}
