package io.sengokudaikon.isn.compendium.usecases

import io.sengokudaikon.isn.compendium.persistence.cache.InMemoryCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

open class CachingUseCase : KoinComponent {

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
