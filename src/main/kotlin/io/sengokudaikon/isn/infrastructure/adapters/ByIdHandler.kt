package io.sengokudaikon.isn.infrastructure.adapters

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

abstract class ByIdHandler<R : Model, Q : Query.ById<R>, P : ReadPort<Q, R>> : QueryHandler() {
    abstract override val useCase: P

    override suspend fun handle(call: ApplicationCall) {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Missing id")
        val query = createQuery(id)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.toResponse<Model>() },
            ),
        )
    }

    abstract fun createQuery(id: String): Q
}
