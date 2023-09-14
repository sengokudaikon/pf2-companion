package io.sengokudaikon.dbfinder.domain.character.ancestry.cache

import arrow.core.Either
import com.github.benmanes.caffeine.cache.AsyncCacheLoader
import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

object AncestryModelCache {
    val cache: AsyncLoadingCache<String, List<Ancestry>> = Caffeine.newBuilder()
        .maximumSize(100)
        .expireAfterWrite(60, TimeUnit.MINUTES)
        .buildAsync(AncestryModelLoader())

    private class AncestryModelLoader :
        AsyncCacheLoader<String, List<Ancestry>>, KoinComponent {
        val repository: AncestryRepository by inject()
        override fun asyncLoad(key: String, executor: Executor): CompletableFuture<out List<Ancestry>> {
            val (_, _, pageStr, limitStr) = key.split(':')
            val page = pageStr.toInt()
            val limit = limitStr.toInt()
            val result = CompletableDeferred<List<Ancestry>>()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    when (val ancestryResponse = repository.findAll(page, limit)) {
                        is Either.Right -> {
                            result.complete(ancestryResponse.value.map { it.toModel() })
                        }
                        is Either.Left -> {
                            result.completeExceptionally(ancestryResponse.value)
                        }
                    }
                } catch (e: Exception) {
                    result.completeExceptionally(e)
                }
            }

            return result.asCompletableFuture()
        }
    }
}
