package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiAncestryTests : FunSpec({
    test("testGetApiAncestryId") {
        testApplication {
            val response = client.get("/api/ancestry/{id}")
            // Add your assertions here
        }
    }

    test("testGetApiAncestryListPageSize") {
        testApplication {
            val response = client.get("/api/ancestry/list/{page}/{size}")
            // Add your assertions here
        }
    }

    test("testGetApiAncestryNameName") {
        testApplication {
            val response = client.get("/api/ancestry/name/{name}")
            // Add your assertions here
        }
    }
})