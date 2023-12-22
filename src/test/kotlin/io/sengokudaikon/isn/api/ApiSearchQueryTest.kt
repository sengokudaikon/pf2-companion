package io.sengokudaikon.isn.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

class ApiSearchQueryTest : FunSpec({
    val client = HttpClient(CIO)
    test("searchQuery") {
        val response = client.get("/api/search/") {
            parameter("query", "Anadi")
            parameter("filter", "eq(type,'ancestry')")
        }
        response.status.value shouldBe 200
    }
})
