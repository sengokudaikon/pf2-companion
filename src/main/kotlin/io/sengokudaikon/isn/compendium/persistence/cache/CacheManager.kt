package io.sengokudaikon.isn.compendium.persistence.cache

import io.github.crackthecodeabhi.kreds.connection.Endpoint
import io.github.crackthecodeabhi.kreds.connection.KredsClient
import io.github.crackthecodeabhi.kreds.connection.newClient
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import org.jetbrains.exposed.sql.exposedLogger
import org.koin.core.annotation.Single

@Single
class CacheManager(private val redisConfig: RedisConfig) {
    val redisClient: KredsClient? = runBlocking { initRedis() }
    private suspend fun initRedis(): KredsClient? {
        val client = newClient(
            Endpoint(redisConfig.host, redisConfig.port),
        )
        runCatching { client.auth(redisConfig.user, redisConfig.password) }
            .onFailure {
                exposedLogger.error(it.stackTraceToString())
                throw InitializationError("Failed to connect to Redis!", it)
            }
            .onSuccess {
                exposedLogger.info("Successfully connected to Redis!")
                return client
            }
        return null
    }

    fun <T> createRedisCache(serializer: KSerializer<T>, loaderFunc: suspend () -> T): RedisCache<T> {
        return RedisCache(redisClient, serializer, loaderFunc)
    }

    fun <T> createCompositeCache(
        serializer: KSerializer<T>,
        loaderFunc: suspend () -> T,
        inMemoryCache: InMemoryCache<T>,
    ): ChainCache<T> {
        val redisCache = createRedisCache(serializer, loaderFunc)
        return ChainCache(redisCache, inMemoryCache)
    }
}
