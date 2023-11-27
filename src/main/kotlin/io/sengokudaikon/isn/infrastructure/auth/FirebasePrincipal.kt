package io.sengokudaikon.isn.infrastructure.auth

import io.ktor.server.auth.*

data class FirebasePrincipal(val uid: String) : Principal
