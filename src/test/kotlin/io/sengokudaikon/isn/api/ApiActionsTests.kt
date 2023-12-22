package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiActionsTests : FunSpec({
    test("testGetApiActionsId") {
        testApplication {
            val response = client.get("/api/actions/") {
                parameter("id", "656df8cf781d41ad2bc63665")
            }
            response.status.value shouldBe 200
        }
    }

    test("testGetApiActionsListPageSize") {
        testApplication {
            val response = client.get("/api/actions/list/") {
                parameter("page", "1")
                parameter("size", "10")
            }
            response.status.value shouldBe 200
        }
    }

    test("testGetApiActionsNameName") {
        testApplication {
            val response = client.get("/api/actions/name/") {
                parameter("name", "Reconnoiter")
            }
            response.status.value shouldBe 200
        }
    }
})