package io.sengokudaikon.isn.infrastructure.operations

import kotlinx.serialization.Serializable

interface Command {
    @Serializable
    data class Create<D, T>(val dto: D) : Command

    @Serializable
    data class Update<D, T>(val dto: D) : Command

    @Serializable
    data class Delete<D, T>(val dto: D) : Command
}
