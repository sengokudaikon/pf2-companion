package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiAuthTest : FunSpec({
    test("auth") {
        testApplication {
            client.get("/api/auth").apply {
                this.status.value shouldBe 200
            }
        }
    }
})
