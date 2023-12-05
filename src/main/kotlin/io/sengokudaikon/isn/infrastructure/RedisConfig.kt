package io.sengokudaikon.isn.infrastructure

class RedisConfig(
    val host: String,
    val port: Int,
    val password: String? = null,
    val user: String? = null,
)
