package io.sengokudaikon.isn.api.story.compendium.steps

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiBackgroundTests : FunSpec({
    test("testGetApiBackgroundId") {
        testApplication {
            val response = client.get("/api/background/{id}")
            // Add your assertions here
        }
    }

    test("testGetApiBackgroundListPageSize") {
        testApplication {
            val response = client.get("/api/background/list")
            // Add your assertions here
        }
    }

    test("testGetApiBackgroundNameName") {
        testApplication {
            val response = client.get("/api/background")
            // Add your assertions here
        }
    }
})