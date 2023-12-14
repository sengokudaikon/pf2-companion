package io.sengokudaikon.isn.infrastructure.adapters

import io.ktor.server.application.*
import io.sengokudaikon.isn.infrastructure.ports.ReadPort
import org.koin.core.component.KoinComponent

abstract class QueryHandler : KoinComponent {
    protected abstract val useCase: ReadPort<*, *>
    abstract suspend fun handle(call: ApplicationCall)
}
