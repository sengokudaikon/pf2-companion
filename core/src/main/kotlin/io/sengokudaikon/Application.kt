package io.sengokudaikon

import com.github.dimitark.ktor.routing.ktorRoutingAnnotationConfig
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.sengokudaikon.infrastructure.CoreModule
import io.sengokudaikon.infrastructure.DatabaseFactory
import io.sengokudaikon.infrastructure.DbConfig
import io.sengokudaikon.plugins.configureHTTP
import io.sengokudaikon.plugins.configureMonitoring
import io.sengokudaikon.plugins.configureRouting
import io.sengokudaikon.plugins.configureSecurity
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(Koin) {
        modules(CoreModule().module)
    }
    val dotenv = dotenv()
    val config = DbConfig(
        driver = dotenv["DB_DRIVER"],
        url = dotenv["DB_URL"],
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
}
