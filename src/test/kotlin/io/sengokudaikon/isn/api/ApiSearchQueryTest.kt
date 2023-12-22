package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiSearchQueryTest : FunSpec({

    test("searchQuery") {
        testApplication {
            client.get("/api/search/"){
                parameter("query", "test")
            }.apply {
                this.status.value shouldBe 200
            }
        }
    }
})
