package io.sengokudaikon.isn

import com.codahale.metrics.Slf4jReporter
import com.codahale.metrics.Timer
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.metrics.dropwizard.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.LongTaskTimer
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import kotlinx.serialization.Serializable
import org.slf4j.event.Level
import java.util.concurrent.TimeUnit

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
        callIdMdc("call-id")
    }
    install(CallId) {
        header(HttpHeaders.XRequestId)
        verify { callId: String ->
            callId.isNotEmpty()
        }
    }
    install(DropwizardMetrics) {
        Slf4jReporter.forRegistry(registry)
            .outputTo(this@configureMonitoring.log)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build()
            .start(1000, TimeUnit.SECONDS)
    }
    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)
    install(MicrometerMetrics) {
        registry = appMicrometerRegistry
        // ...
    }
    routing {
        get("/metrics-micrometer") {
            call.respond(appMicrometerRegistry.scrape())
        }
        get("/metrics-http") {
            val meters = appMicrometerRegistry.find("ktor.http.server.requests.active").meters()
            val snapshots = meters.flatMap { meter ->
                when (meter) {
                    is Timer -> meter.takeIf { it.count > 0 }?.let {
                        it.measure().map { measurement ->
                            MetricSnapshot(measurement.statistic.name, measurement.value)
                        }
                    }

                    is DistributionSummary -> meter.takeIf { it.count() > 0 }?.let {
                        it.measure().map { measurement ->
                            MetricSnapshot(measurement.statistic.name, measurement.value)
                        }
                    }

                    is LongTaskTimer -> meter.takeIf { it.activeTasks() > 0 }?.let {
                        it.measure().map { measurement ->
                            MetricSnapshot(measurement.statistic.name, measurement.value)
                        }
                    }

                    else -> null
                } ?: emptyList()
            }
            call.respond(snapshots)
        }
    }
}

@Serializable
data class MetricSnapshot(val name: String, val value: Double)
