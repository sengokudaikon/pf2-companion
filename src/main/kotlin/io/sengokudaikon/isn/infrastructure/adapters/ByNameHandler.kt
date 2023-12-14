package io.sengokudaikon.isn.infrastructure.adapters

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

abstract class ByNameHandler<R : Model, Q : Query.ByName<R>, P : ReadPort<Q, R>> : QueryHandler() {
    abstract override val useCase: P

    override suspend fun handle(call: ApplicationCall) {
        val id = call.parameters["id"]
        val name = call.parameters["name"] ?: throw IllegalArgumentException("Missing name")
        val query = createQuery(name, id)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.toResponse<Model>() },
            ),
        )
    }

    abstract fun createQuery(name: String, id: String?): Q
}
