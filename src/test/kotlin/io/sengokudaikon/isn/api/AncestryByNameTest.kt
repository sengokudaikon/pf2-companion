package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.server.testing.*

class AncestryByNameTest : FunSpec({

    test("ancestryByName") {
        testApplication {
            client.get("/api/ancestry/name/{name}").apply {
                this.status.value shouldBe 200
            }
        }
    }
})
