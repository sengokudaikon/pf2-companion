package io.sengokudaikon.isn

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureSecurity() {
    authentication {
//        firebase {
//            validate { firebaseToken ->
//                val userId = firebaseToken.uid
//                if (userId != null) {
//                    FirebasePrincipal(userId)
//                } else {
//                    null
//                }
//            }
//        }
    }
}
