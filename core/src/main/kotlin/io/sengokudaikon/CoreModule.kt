package io.sengokudaikon

import com.github.dimitark.ktor.routing.ktorRoutingAnnotationConfig
import io.ktor.server.application.*

fun Application.coreModule() {
    ktorRoutingAnnotationConfig()
}
