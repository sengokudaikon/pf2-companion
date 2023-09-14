package io.sengokudaikon.dbfinder.persistence.character.ancestry.cache

import com.github.benmanes.caffeine.cache.AsyncCacheLoader
import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.Ancestry
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.future.asCompletableFuture
import kotlinx.coroutines.launch
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

object AncestryCache {
    val cache: AsyncLoadingCache<String, List<Ancestry>> = Caffeine.newBuilder()
        .maximumSize(100)
        .expireAfterWrite(60, TimeUnit.MINUTES)
        .buildAsync(AncestryLoader())

    private class AncestryLoader : AsyncCacheLoader<String, List<Ancestry>> {
        override fun asyncLoad(key: String, executor: Executor): CompletableFuture<List<Ancestry>> {
            val (_, _, pageStr, limitStr) = key.split(':')
            val offset = (pageStr.toInt() - 1).toLong()
            val limit = limitStr.toInt()

            val result = CompletableDeferred<List<Ancestry>>()

            CoroutineScope(IO).launch {
                try {
                    val data = suspendedTransactionAsync {
                        Ancestry.all()
                            .with(
                                Ancestry::abilityBoosts,
                                Ancestry::abilityFlaws,
                                Ancestry::traits,
                                Ancestry::languages,
                                Ancestry::physicalFeatures,
                                Ancestry::rules,
                            )
                            .limit(limit, offset)
                            .toList()
                    }.await()

                    // Complete the deferred result
                    result.complete(data)
                } catch (e: Exception) {
                    result.completeExceptionally(e)
                }
            }

            // Return the completable future
            return result.asCompletableFuture()
        }
    }
}
