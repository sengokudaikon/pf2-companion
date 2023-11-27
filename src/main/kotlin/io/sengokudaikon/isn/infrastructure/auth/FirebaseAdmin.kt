package io.sengokudaikon.isn.infrastructure.auth

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.InputStream
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object FirebaseAdmin {
    @OptIn(ExperimentalEncodingApi::class)
    fun init(): FirebaseApp {
        var serviceAccountStream: InputStream? =
            this::class.java.classLoader.getResourceAsStream("adminsdk.json")

        if (serviceAccountStream == null) {
            val serviceAccountContent = Base64.decode(System.getenv("FIREBASE_SERVICE_ACCOUNT"))
            serviceAccountStream = serviceAccountContent.inputStream()
        }

        return FirebaseApp.initializeApp(
            FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build(),
        )
    }
}
