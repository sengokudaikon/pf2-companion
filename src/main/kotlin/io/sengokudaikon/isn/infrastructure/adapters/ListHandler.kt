package io.sengokudaikon.isn.infrastructure.adapters

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.sengokudaikon.isn.infrastructure.domain.Model
import io.sengokudaikon.isn.infrastructure.operations.Query
import io.sengokudaikon.isn.infrastructure.ports.ReadPort

abstract class ListHandler<R : Model, Q : Query.All<List<R>>, P : ReadPort<Q, List<R>>> : QueryHandler() {
    abstract override val useCase: P
    override suspend fun handle(call: ApplicationCall) {
        val id = call.parameters["id"]
        val page = call.parameters["page"]?.toInt() ?: 1
        val size = call.parameters["size"]?.toInt() ?: 10
        val filterQuery = call.parameters["filters"]
        val sort = call.parameters["sort"]
        val query = createQuery(page, size, filterQuery, sort, id)
        val result = useCase.execute(query)
        call.respond(
            result.fold(
                onFailure = { throw it },
                onSuccess = { it.map { model -> model.toResponse<Model>() } },
            ),
        )
    }

    abstract fun createQuery(page: Int, size: Int, filters: String?, sort: String?, id: String? = null): Q
}