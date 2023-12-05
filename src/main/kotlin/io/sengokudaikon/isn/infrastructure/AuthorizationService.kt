package io.sengokudaikon.isn.infrastructure

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.app.domain.user.UserRole
import io.sengokudaikon.isn.app.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.isn.infrastructure.auth.FirebasePrincipal
import io.sengokudaikon.isn.infrastructure.errors.UserException
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

    suspend fun getUserId(call: ApplicationCall): String {
        val uid = call.principal<FirebasePrincipal>()?.uid ?: throw UserException.Unauthorized("Unauthorized user")
        val user = userRepository.findByUid(uid).getOrNull() ?: throw UserException.NotFound()
        return user.id
    }
}
