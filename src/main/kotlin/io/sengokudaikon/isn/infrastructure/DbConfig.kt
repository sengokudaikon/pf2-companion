package io.sengokudaikon.isn.infrastructure

class DbConfig(
    val host: String,
    val port: Int,
    val name: String,
    val username: String? = null,
    val password: String? = null
)
