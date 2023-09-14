package io.sengokudaikon.kfinder

import com.github.dimitark.ktor.routing.ktorRoutingAnnotationConfig
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.sengokudaikon.dbfinder.fixtures.AncestryFixtureLoader
import io.sengokudaikon.dbfinder.infrastructure.DbModule
import io.sengokudaikon.dbfinder.persistence.character.ancestry.repository.AncestryRepository
import io.sengokudaikon.kfinder.infrastructure.CoreModule
import io.sengokudaikon.kfinder.infrastructure.DatabaseFactory
import io.sengokudaikon.kfinder.infrastructure.DbConfig
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.ksp.generated.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val server = GlobalScope.launch {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            launch { module() }
        }.start(wait = true)
    }
    runBlocking {
        server.join()
    }
}

fun Application.module() {
    install(Koin) {
        modules(
            CoreModule().module,
            DbModule().module,
        )
    }

    val dotenv = dotenv()
    val config = DbConfig(
        driver = dotenv["DB_DRIVER"],
        url = "jdbc:postgresql://"
            .plus(dotenv["DB_HOST"]).plus(":").plus(dotenv["DB_PORT"]).plus("/").plus(dotenv["DB_NAME"]),
        username = dotenv["DB_USER"],
        password = dotenv["DB_PASSWORD"],
    )
    val runMigrations = dotenv["RUN_MIGRATIONS"]?.toBoolean() ?: false
    DatabaseFactory.init(runMigrations = runMigrations, dbConfig = config)
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureRouting()
    ktorRoutingAnnotationConfig()
    val ancestryRepository: AncestryRepository by inject()
    runBlocking { AncestryFixtureLoader(ancestryRepository).insertIntoDatabase() }
}
