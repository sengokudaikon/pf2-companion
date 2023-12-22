package io.sengokudaikon.isn.api.story.compendium.steps

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiHeritageTests : FunSpec({
    test("testGetApiHeritageId") {
        testApplication {
            val response = client.get("/api/heritage/{id}")
            // Add your assertions here
        }
    }

    test("testGetApiHeritageListPageSize") {
        testApplication {
            val response = client.get("/api/heritage/list/{page}/{size}")
            // Add your assertions here
        }
    }

    test("testGetApiHeritageNameName") {
        testApplication {
            val response = client.get("/api/heritage/name/{name}")
            // Add your assertions here
        }
    }
})