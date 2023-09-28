package io.sengokudaikon.dbfinder.persistence.cache

import com.github.benmanes.caffeine.cache.AsyncCacheLoader
import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class InMemoryCache<T>(
    private val loaderFunc: suspend () -> T,
) : KoinComponent, Cache<T> {

    val runner: AsyncLoadingCache<String, T> = Caffeine.newBuilder()
        .maximumSize(100)
        .expireAfterWrite(60, TimeUnit.MINUTES)
        .executor(IO.asExecutor())
        .buildAsync(CacheLoader())

    override suspend fun get(key: String): T? {
        return try {
            runner.getIfPresent(key)?.await()
        } catch (exception: Exception) {
            println("Error retrieving from cache: ${exception.localizedMessage}")
            null
        }
    }

    override suspend fun set(key: String, value: T) {
        try {
            runner.put(key, CompletableFuture.completedFuture(value))
        } catch (exception: Exception) {
            println("Error setting value in cache: ${exception.localizedMessage}")
        }
    }

    inner class CacheLoader : AsyncCacheLoader<String, T> {
        override fun asyncLoad(key: String, executor: Executor): CompletableFuture<T> {
            val result = CompletableFuture<T>()
            CoroutineScope(IO).launch {
                try {
                    val value = loaderFunc()
                    result.complete(value)
                } catch (e: Exception) {
                    result.completeExceptionally(e)
                }
            }
            return result
        }

        override fun asyncReload(key: String, oldValue: T, executor: Executor?): CompletableFuture<out T> {
            return asyncLoad(key, executor!!)
        }
    }
}
