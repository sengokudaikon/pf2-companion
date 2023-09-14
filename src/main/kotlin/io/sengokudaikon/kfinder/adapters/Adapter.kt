package io.sengokudaikon.kfinder.adapters

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.sengokudaikon.kfinder.domain.user.model.UserRole
import io.sengokudaikon.kfinder.infrastructure.AuthorizationService
import io.sengokudaikon.kfinder.infrastructure.errors.UserException
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
        return call.principal<JWTPrincipal>()?.get("user") ?: throw UserException.Unauthorized("Unauthorized user")
    }
}
