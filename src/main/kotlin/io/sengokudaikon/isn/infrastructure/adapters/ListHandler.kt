package io.sengokudaikon.isn.infrastructure.adapters

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

abstract class ListHandler<R : Collection<Model>, Q : Query.All<R>, P : ReadPort<Q, R>> : QueryHandler() {
    abstract override val useCase: P
    override suspend fun handle(call: ApplicationCall) {
        val id = call.parameters["id"]
        val page = call.parameters["page"]?.toInt() ?: throw IllegalArgumentException("Missing page")
        val size = call.parameters["size"]?.toInt() ?: throw IllegalArgumentException("Missing size")
        val query = createQuery(page, size, id)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.map { model -> model.toResponse<Model>() } },
            ),
        )
    }

    abstract fun createQuery(page: Int, size: Int, id: String? = null): Q
}
