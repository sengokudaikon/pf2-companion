package io.sengokudaikon.dbfinder.persistence.cache

import io.github.crackthecodeabhi.kreds.connection.KredsClient
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonPrimitive

class RedisCache<T>(
    private val redisClient: KredsClient? = null,
    private val serializer: KSerializer<T>,
    val loaderFunc: suspend () -> T,
) : Cache<T> {
    override suspend fun get(key: String): T? {
        val value = redisClient?.get(key)
        return if (value != null) {
            Json.decodeFromJsonElement(serializer, Json.parseToJsonElement(value))
        } else {
            loaderFunc.let { loaderFunc() }
        }
    }

    override suspend fun set(key: String, value: T) {
        val jsonValue = Json.encodeToJsonElement(serializer, value).jsonPrimitive.content
        redisClient?.set(key, jsonValue)
    }
}
