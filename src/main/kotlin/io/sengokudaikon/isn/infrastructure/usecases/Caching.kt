package io.sengokudaikon.isn.infrastructure.usecases

import io.sengokudaikon.isn.compendium.persistence.cache.InMemoryCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

interface Caching : KoinComponent {

    @Suppress("unused")
    suspend fun <T : Any> withCache(
        cacheKey: String,
        loaderFunc: suspend () -> T,
    ): T {
        val inMemoryCache = InMemoryCache(loaderFunc)
        return withContext(Dispatchers.IO) {
            val result: T = inMemoryCache.get(cacheKey) ?: loaderFunc()
            result
        }.also {
            inMemoryCache.set(cacheKey, it)
        }
    }
}
