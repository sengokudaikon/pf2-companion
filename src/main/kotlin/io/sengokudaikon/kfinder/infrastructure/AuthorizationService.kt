package io.sengokudaikon.kfinder.infrastructure

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.sengokudaikon.kfinder.domain.user.model.UserRole
import io.sengokudaikon.kfinder.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.shared.infrastructure.errors.UserException
import kotlinx.uuid.UUID
import org.koin.core.annotation.Single

@Single
class AuthorizationService(private val userRepository: UserRepositoryPort) {

    suspend fun <T> authorize(
        call: ApplicationCall,
        requiredRoles: List<UserRole>,
        block: suspend () -> T,
    ) {
        val user = userRepository.findById(getUserId(call)).getOrNull() ?: throw UserException.NotFound()
        if (user.role in requiredRoles) {
            block()
        } else {
            call.respond(HttpStatusCode.Forbidden, "You do not have the required role to access this resource.")
        }
    }

    suspend fun getUserId(call: ApplicationCall): UUID {
        val uid = call.principal<JWTPrincipal>()?.get("user") ?: throw UserException.Unauthorized("Unauthorized user")
        val user = userRepository.findByUid(uid).getOrNull() ?: throw UserException.NotFound()
        return user.id.value
    }
}
