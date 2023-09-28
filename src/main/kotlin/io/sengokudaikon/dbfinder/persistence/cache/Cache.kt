package io.sengokudaikon.dbfinder.persistence.cache

interface Cache<T> {
    suspend fun get(key: String): T?
    suspend fun set(key: String, value: T)
}
