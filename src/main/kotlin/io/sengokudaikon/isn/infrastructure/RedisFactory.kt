package io.sengokudaikon.isn.infrastructure

import io.github.crackthecodeabhi.kreds.connection.Endpoint
import io.github.crackthecodeabhi.kreds.connection.KredsClient
import io.github.crackthecodeabhi.kreds.connection.newClient
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException

object RedisFactory {
    suspend fun init(redisConfig: RedisConfig): KredsClient {
        val client = newClient(
            Endpoint(
                redisConfig.host,
                redisConfig.port,
            ),
        )
        if (!redisConfig.user.isNullOrEmpty() && !redisConfig.password.isNullOrEmpty()) {
            runCatching { client.auth(redisConfig.user, redisConfig.password) }
                .onFailure {
                    logger.error(it.stackTraceToString())
                    throw DatabaseException.ConnectionFailed(
                        KredsClient::class.qualifiedName,
                        previous = it,
                    )
                }
                .onSuccess {
                    logger.info("Successfully connected to Redis!")
                    return client
                }
        }
        logger.info("No Redis credentials provided, skipping authentication...")
        return client
    }
}
