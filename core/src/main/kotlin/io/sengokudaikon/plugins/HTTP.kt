package io.sengokudaikon.plugins

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.conditionalheaders.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.hsts.*
import io.ktor.server.plugins.httpsredirect.*
import io.ktor.server.plugins.partialcontent.*
import kotlinx.serialization.json.Json

private const val MAX_RANGE: Int = 10
private const val MAX_AGE_SECONDS: Int = 24 * 60 * 60
private const val SSL_PORT: Int = 443
fun Application.configureHTTP() {
    install(CachingHeaders) {
        options { _, outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = MAX_AGE_SECONDS))
                else -> null
            }
        }
    }
    install(ConditionalHeaders)
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.AccessControlAllowHeaders)
        allowHeader(HttpHeaders.AccessControlAllowMethods)
        allowHeader(HttpHeaders.AccessControlAllowCredentials)
        allowHeader(HttpHeaders.Accept)
        allowHeader(HttpHeaders.AcceptLanguage)
        allowCredentials = true
    }
    install(DefaultHeaders) {
        header("X-Engine", "Ktor")
    }
    install(HSTS) {
        includeSubDomains = true
    }
    install(HttpsRedirect) {
        sslPort = SSL_PORT
        permanentRedirect = true
    }
    install(PartialContent) {
        maxRangeCount = MAX_RANGE
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },
        )
    }
    handleErrors()
}