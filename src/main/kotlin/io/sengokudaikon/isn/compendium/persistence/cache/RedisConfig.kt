package io.sengokudaikon.isn.compendium.persistence.cache

import io.github.cdimascio.dotenv.dotenv

object RedisConfig {
    val dotenv = dotenv()
    val host: String = dotenv.get("REDIS_HOST")
    val port: Int = dotenv.get("REDIS_PORT").toInt()
    val password: String = dotenv.get("REDIS_PASSWORD")
    val user: String = dotenv.get("REDIS_USER")
}
