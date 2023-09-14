package io.sengokudaikon.shared.operations

interface Command<D, T> {
    data class Create<D, T>(val dto: D) : Command<D, T>
    data class Update<D, T>(val dto: D) : Command<D, T>
    data class Delete<D, T>(val dto: D) : Command<D, T>
}
