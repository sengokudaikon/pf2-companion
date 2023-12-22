package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiClassFeatsTests : FunSpec({
    test("testGetApiClassfeatsName") {
        testApplication {
            val response = client.get("/api/classfeats/{name}")
            // Add your assertions here
        }
    }

    test("testGetApiClassfeatsPageSize") {
        testApplication {
            val response = client.get("/api/classfeats/{page}/{size}")
            // Add your assertions here
        }
    }
})