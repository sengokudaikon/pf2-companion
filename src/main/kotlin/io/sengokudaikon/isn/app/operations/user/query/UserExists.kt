package io.sengokudaikon.isn.app.operations.user.query

import io.ktor.resources.*

@Resource("/api/auth/doesUserExist")
data class UserExists(
    val identifier: String,
)
