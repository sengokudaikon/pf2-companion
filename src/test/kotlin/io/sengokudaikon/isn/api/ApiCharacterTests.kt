package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiCharacterTests : FunSpec({
    test("testGetApiCharacterId") {
        testApplication {
            val response = client.get("/api/character/{id}")
            // Add your assertions here
        }
    }

    test("testGetApiCharacterListPageSize") {
        testApplication {
            val response = client.get("/api/character/list/{page}/{size}")
            // Add your assertions here
        }
    }
})