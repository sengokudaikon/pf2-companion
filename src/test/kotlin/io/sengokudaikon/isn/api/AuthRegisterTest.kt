package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.server.testing.*

class AuthRegisterTest : FunSpec({
    test("register") {
        testApplication {
            client.get("/api/auth/register").apply {
                this.status.value shouldBe 200
            }
        }
    }
})
