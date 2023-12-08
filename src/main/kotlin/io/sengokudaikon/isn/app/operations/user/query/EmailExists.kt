package io.sengokudaikon.isn.app.operations.user.query

import io.ktor.resources.*

@Resource("/api/auth/doesEmailExist")
data class EmailExists(
    val email: String,
)
