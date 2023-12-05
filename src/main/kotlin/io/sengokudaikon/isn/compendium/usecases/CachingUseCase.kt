package io.sengokudaikon.isn.compendium.usecases

import com.github.kittinunf.result.runCatching
import io.sengokudaikon.isn.compendium.persistence.cache.CacheManager
import io.sengokudaikon.isn.compendium.persistence.cache.InMemoryCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class CachingUseCase : KoinComponent {
    private val cacheManager: CacheManager by inject()

    @Suppress("unused")
    suspend fun <T : Any> withCache(
        cacheKey: String,
        serializer: KSerializer<T>,
        loaderFunc: suspend () -> T,
    ): T {
        val inMemoryCache = InMemoryCache(loaderFunc)
        val cache = cacheManager.createCompositeCache(serializer, loaderFunc, inMemoryCache)
        return runCatching {
            withContext(Dispatchers.IO) {
                val result: T = cache.get(cacheKey) ?: loaderFunc()
                result
            }
        }.get() ?: loaderFunc()
    }
}
