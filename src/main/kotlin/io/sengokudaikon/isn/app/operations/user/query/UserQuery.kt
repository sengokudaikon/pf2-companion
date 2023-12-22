package io.sengokudaikon.isn.app.operations.user.query

import io.ktor.resources.*
import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.infrastructure.operations.Query

interface UserQuery : Query {
    @Resource("/api/user/list/{page}/{size}")
    data class All(
        override val page: Int,
        override val size: Int,
        override val filters: String? = null,
    ) : Query.All<List<User>>, UserQuery

    @Resource("/api/user/{id}")
    data class ById(
        override val id: String,
    ) : Query.ById<User>, UserQuery

    @Resource("/api/user/email/{email}")
    data class ByEmail(
        val email: String,
    ) : UserQuery

    @Resource("/api/user/username/{name}")
    data class ByName(
        override val name: String,
    ) : Query.ByName<User>, UserQuery
}
