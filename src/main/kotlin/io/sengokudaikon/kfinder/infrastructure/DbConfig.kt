package io.sengokudaikon.kfinder.infrastructure

class DbConfig(
    val driver: String,
    val url: String,
    val username: String,
    val password: String,
    val host: String? = null,
    val port: Int? = null,
    val name: String? = null,
)
