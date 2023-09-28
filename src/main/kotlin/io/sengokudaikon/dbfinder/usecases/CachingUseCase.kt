package io.sengokudaikon.dbfinder.usecases

import com.github.kittinunf.result.runCatching
import io.sengokudaikon.dbfinder.persistence.cache.CacheManager
import io.sengokudaikon.dbfinder.persistence.cache.InMemoryCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class CachingUseCase : KoinComponent {
    private val cacheManager: CacheManager by inject()
    suspend fun <T : Any> withCache(
        cacheKey: String,
        serializer: KSerializer<T>,
        loaderFunc: suspend () -> T,
    ): T {
        val inMemoryCache = InMemoryCache(loaderFunc)
        val cache = if (cacheManager.redisClient != null) {
            cacheManager.createCompositeCache(serializer, loaderFunc, inMemoryCache)
        } else {
            inMemoryCache
        }
        return runCatching {
            withContext(Dispatchers.IO) {
                val result: T = cache.get(cacheKey) ?: loaderFunc()
                result
            }
        }.get() ?: loaderFunc()
    }
}
