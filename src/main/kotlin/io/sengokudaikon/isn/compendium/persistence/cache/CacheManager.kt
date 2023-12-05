package io.sengokudaikon.isn.compendium.persistence.cache

import io.github.crackthecodeabhi.kreds.connection.KredsClient
import kotlinx.serialization.KSerializer
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Single
class CacheManager : KoinComponent {
    val redisClient: KredsClient by inject()

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
