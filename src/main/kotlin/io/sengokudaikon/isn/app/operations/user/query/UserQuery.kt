package io.sengokudaikon.isn.app.operations.user.query

import io.ktor.resources.*
import io.sengokudaikon.isn.infrastructure.operations.Query

interface UserQuery : Query {
    @Resource("/api/user/list/{page}/{size}")
    data class FindAll(
        val page: Int,
        val size: Int,
    ) : Query

    @Resource("/api/user/{id}")
    data class FindById(
        val id: String,
    ) : Query

    @Resource("/api/user/email/{email}")
    data class FindByEmail(
        val email: String,
    ) : Query

    @Resource("/api/user/username/{username}")
    data class FindByUsername(
        val username: String,
    ) : Query
}
