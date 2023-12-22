package io.sengokudaikon.isn.api.story.compendium.steps

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiActionsTests : BehaviorSpec({
    val client = HttpClient(CIO)

    Given("actions API endpoint") {
        When("a GET request is made with a valid ID") {
            val response: HttpResponse = client.get("http://localhost:8083/api/actions/{id}") {
                parameter("id", "658474cde4415ee40ede847f")
            }

            Then("the response status should be 200") {
                response.status.value shouldBe 200
            }
        }

        When("a GET request is made with an invalid ID") {
            val response: HttpResponse = client.get("http://localhost:8083/api/actions/") {
                parameter("id", "656df8cf781d41ad2bc63666")
            }

            Then("the response status should be 404") {
                response.status.value shouldBe 404
            }
        }

        When("a GET request is made to List with page and size parameters") {
            val response: HttpResponse = client.get("http://localhost:8083/api/actions/list") {
                parameter("page", "1")
                parameter("size", "10")
            }

            Then("the response status should be 200") {
                response.status.value shouldBe 200
            }
        }

        When("a GET request is made with a valid name") {
            val response: HttpResponse = client.get("http://localhost:8083/api/actions") {
                parameter("name", "Reconnoiter")
            }

            Then("the response status should be 200") {
                response.status.value shouldBe 200
            }
        }

        When("a GET request is made with an invalid name") {
            val response: HttpResponse = client.get("http://localhost:8083/api/actions/name/") {
                parameter("name", "absghasa")
            }

            Then("the response status should be 404") {
                response.status.value shouldBe 404
            }
        }
    }
})