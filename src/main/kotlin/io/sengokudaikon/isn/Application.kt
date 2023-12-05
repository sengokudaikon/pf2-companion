package io.sengokudaikon.isn

import com.github.dimitark.ktor.routing.ktorRoutingAnnotationConfig
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.sengokudaikon.isn.infrastructure.CoreModule
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.DbConfig
import io.sengokudaikon.isn.infrastructure.RedisConfig
import io.sengokudaikon.isn.infrastructure.RedisFactory
import io.sengokudaikon.isn.infrastructure.auth.FirebaseAdmin
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.dsl.module
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin
val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("Caught $throwable")
}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val server = GlobalScope.launch(exceptionHandler) {
        embeddedServer(
            Netty,
            port = 8081,
            host = "0.0.0.0",
        ) {
            launch { exec() }
        }.start(wait = true)
    }
    runBlocking {
        server.join()
    }
}

@OptIn(ExperimentalSerializationApi::class)
suspend fun Application.exec() {
    val env = System.getenv("ENV")

    val dotenv = when (env) {
        "local" -> dotenv { filename = ".env.local" }
        "dev" -> dotenv { filename = ".env.dev" }
        "prod" -> dotenv { filename = ".env.prod" }
        else -> dotenv { filename = ".env" }
    }
    val config = DbConfig(
        host = dotenv["DB_HOST"],
        port = dotenv["DB_PORT"].toInt(),
        name = dotenv["DB_NAME"],
    )
    val redisConfig = RedisConfig(
        host = dotenv["REDIS_HOST"],
        port = dotenv["REDIS_PORT"].toInt(),
        password = dotenv["REDIS_PASSWORD"],
        user = dotenv["REDIS_USER"],
    )
    val dbInit = coroutineScope {
        async(IO) {
            try {
                DatabaseFactory.init(dbConfig = config)
            } catch (e: Exception) {
                io.sengokudaikon.isn.infrastructure.logger.info(e.localizedMessage)
            }
        }
    }
    dbInit.await()
    val redis = runBlocking {
        RedisFactory.init(redisConfig = redisConfig)
    }

    FirebaseAdmin.init()
    install(Koin) {
        modules(
            module {
                single {
                    redis
                }
            },
            CoreModule().module,
        )
    }
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureRouting()
    ktorRoutingAnnotationConfig()

    coroutineScope {
        async(IO) {
            DatabaseFactory.createIndexes()
        }
    }
}
