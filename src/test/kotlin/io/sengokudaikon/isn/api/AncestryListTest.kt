package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.server.testing.*

class AncestryListTest : FunSpec({
    test("testGetApiAncestryListPageSize") {
        testApplication {
            val response = client.get("/api/ancestry/list/1/10")
            response.status shouldBe io.ktor.http.HttpStatusCode.OK
        }
    }
})
