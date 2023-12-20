package io.sengokudaikon.isn

import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.sengokudaikon.isn.infrastructure.CoreModule
import io.sengokudaikon.isn.infrastructure.DatabaseFactory
import io.sengokudaikon.isn.infrastructure.DbConfig
import io.sengokudaikon.isn.infrastructure.auth.FirebaseAdmin
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.KoinApplicationStarted
import org.koin.ktor.plugin.KoinApplicationStopPreparing
import org.koin.ktor.plugin.KoinApplicationStopped
import org.koin.logger.slf4jLogger

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val server = GlobalScope.launch {
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
    val dbInit = coroutineScope {
        async(IO) {
            try {
                DatabaseFactory.init(dbConfig = config)
            } catch (e: Exception) {
                log.info(e.localizedMessage)
            }
        }
    }
    dbInit.await()
    startKoin {
        slf4jLogger()
        modules(
            CoreModule().module,
        )
    }

    FirebaseAdmin.init()
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureRouting()
    configureTelemetry()

    coroutineScope {
        async(IO) {
            DatabaseFactory.createIndexes()
        }
    }.await()
}

private fun Application.configureTelemetry() {
    environment.monitor.subscribe(ApplicationStarting) {
        log.info("Application starting...")
    }
    environment.monitor.subscribe(ApplicationStopping) {
        log.info("Application stopping...")
    }
    environment.monitor.subscribe(ApplicationStarted) {
        log.info("Application started")
    }

    environment.monitor.subscribe(KoinApplicationStarted) {
        log.info("Koin started")
    }
    environment.monitor.subscribe(KoinApplicationStopPreparing) {
        log.info("Koin stopping...")
    }
    environment.monitor.subscribe(KoinApplicationStopped) {
        log.info("Koin stopped.")
    }
}
