package io.sengokudaikon.isn.app.operations.user.query

import io.ktor.resources.*
import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.infrastructure.operations.Query

interface UserQuery : Query {
    @Resource("/api/users")
    data class All(
        override val page: Int,
        override val size: Int,
    ) : Query.All<List<User>>, UserQuery {
        override var filters: String? = null
        override var sort: String? = null
    }

    @Resource("/api/user/{id}")
    data class ById(
        override val id: String,
    ) : Query.ById<User>, UserQuery

    @Resource("/api/user")
    data class ByEmail(
        val email: String?,
    ) : UserQuery {}

    @Resource("/api/user")
    data class ByName(
        val name: String,
    ) :  UserQuery {}
}
