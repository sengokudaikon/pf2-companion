package io.sengokudaikon.isn.api.story.compendium.steps

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiClassTests : FunSpec({
    test("testGetApiClassId") {
        testApplication {
            val response = client.get("/api/class/{id}")
            // Add your assertions here
        }
    }

    test("testGetApiClassListPageSize") {
        testApplication {
            val response = client.get("/api/class/list")
            // Add your assertions here
        }
    }

    test("testGetApiClassNameName") {
        testApplication {
            val response = client.get("/api/class")
            // Add your assertions here
        }
    }
})