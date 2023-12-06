package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.server.testing.*

class AncestryByIdTest : FunSpec({

    test("ancestryById") {
        testApplication {
            client.get("/api/ancestry/id/{id}").apply {
                this.status.value shouldBe 200
            }
        }
    }
})
