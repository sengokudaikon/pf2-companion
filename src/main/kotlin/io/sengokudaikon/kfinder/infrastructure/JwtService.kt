package io.sengokudaikon.kfinder.infrastructure

import arrow.core.Either
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.github.cdimascio.dotenv.Dotenv
import io.sengokudaikon.kfinder.domain.user.entity.UserToken
import io.sengokudaikon.kfinder.domain.user.repository.UserTokenRepositoryPort
import org.koin.core.annotation.Single
import java.util.*

@Single
class JwtService(
    private val userTokenRepository: UserTokenRepositoryPort,
) {
    private val env: Dotenv = Dotenv.load()
    private val secret = env["JWT_SECRET"]
    private val issuer = "pathfinder.companion"
    private val validityInMs = 36_000_00 * 10 // 10 hours
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    suspend fun generateToken(id: String): String {
        val token = JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("uid", id)
            .withExpiresAt(getExpiration())
            .sign(algorithm)
        saveToken(id, token)
        return token
    }

    private suspend fun saveToken(id: String, token: String): Either<Throwable, UserToken> {
        return userTokenRepository.saveToken(io.sengokudaikon.kfinder.domain.user.model.UserToken(id, token))
    }

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

    suspend fun validateToken(token: String): Either<Throwable, UserToken> {
        return userTokenRepository.get(token)
            .mapLeft { it }
            .map { it }
    }
}
