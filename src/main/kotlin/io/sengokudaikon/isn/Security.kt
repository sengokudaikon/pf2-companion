package io.sengokudaikon.isn

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.sengokudaikon.isn.infrastructure.auth.FirebasePrincipal
import io.sengokudaikon.isn.infrastructure.auth.firebase

fun Application.configureSecurity() {
    authentication {
        firebase {
            validate { firebaseToken ->
                val userId = firebaseToken.uid
                if (userId != null) {
                    FirebasePrincipal(userId)
                } else {
                    null
                }
            }
        }
    }
}
