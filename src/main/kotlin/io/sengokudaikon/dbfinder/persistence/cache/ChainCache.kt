package io.sengokudaikon.dbfinder.persistence.cache

class ChainCache<T>(
    private val redisCache: RedisCache<T>,
    private val inMemoryCache: InMemoryCache<T>,
) : Cache<T> {

    override suspend fun get(key: String): T? {
        // Check RedisCache
        redisCache.get(key)?.let { return@get it }

        // Check InMemoryCache
        inMemoryCache.get(key)?.let {
            // If found in InMemoryCache, set in RedisCache for subsequent queries
            redisCache.set(key, it)
            return@get it
        }

        // Load the missing value and store in both caches
        redisCache.loaderFunc()?.let { value ->
            inMemoryCache.set(key, value)
            redisCache.set(key, value)
            return@get value
        }

        // Value not found in any cache and unable to load
        return null
    }

    override suspend fun set(key: String, value: T) {
        // Set value in both caches
        inMemoryCache.set(key, value)
        redisCache.set(key, value)
    }
}
