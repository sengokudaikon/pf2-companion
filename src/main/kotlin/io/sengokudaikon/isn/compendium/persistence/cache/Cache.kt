package io.sengokudaikon.isn.compendium.persistence.cache

interface Cache<T> {
    suspend fun get(key: String): T?
    suspend fun set(key: String, value: T)
}
