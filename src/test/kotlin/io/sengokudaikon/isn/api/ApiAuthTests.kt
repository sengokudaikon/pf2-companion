package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiAuthTests : FunSpec({
    test("testGetApiAuthDoesEmailExist") {
        testApplication {
            val response = client.get("/api/auth/doesEmailExist")
            // Add your assertions here
        }
    }

    test("testOptionsApiAuthDoesEmailExist") {
        testApplication {
            val response = client.options("/api/auth/doesEmailExist")
            // Add your assertions here
        }
    }

    test("testGetApiAuthDoesUserExist") {
        testApplication {
            val response = client.get("/api/auth/doesUserExist")
            // Add your assertions here
        }
    }

    test("testOptionsApiAuthDoesUserExist") {
        testApplication {
            val response = client.options("/api/auth/doesUserExist")
            // Add your assertions here
        }
    }

    test("testPostApiAuthRegister") {
        testApplication {
            val response = client.post("/api/auth/register")
            // Add your assertions here
        }
    }
})