package io.sengokudaikon.isn.api.story.compendium.steps
import io.cucumber.java.en.Then
import io.cucumber.java8.En
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.sengokudaikon.isn.compendium.operations.character.ancestry.response.AncestryResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AncestrySteps : En {
    private lateinit var response: HttpResponse
    init {
        Given("a running server") {
        }

        When("a GET request is made with a valid ancestry ID") {
            testApplication {
                client.get("/api/ancestry/{id}") {
                    parameter("id", "60f0a5b3e4b0c9a7a0f3b0a0")
                }.apply {
                    response = this
                }
            }
        }

        When("a GET request is made with a valid ancestry name") {
            testApplication {
                client.get("/api/ancestry/name/{name}") {
                    parameter("name", "Anadi")
                }.apply {
                    response = this
                }
            }
        }

        When("a GET request is made to the list endpoint") {
            testApplication {
                client.get("/api/ancestry/list/{page}/{size}") {
                    parameter("page", 1)
                    parameter("limit", 1)
                }.apply {
                    response = this
                }
            }
        }

        When("a GET request is made with an invalid ancestry ID") {
            testApplication {
                client.get("/api/ancestry/{id}") {
                    parameter("id", "1234567890")
                }.apply {
                    response = this
                }
            }
        }

        When("a GET request is made with an invalid ancestry name") {
            testApplication {
                client.get("/api/ancestry/name/{name}") {
                    parameter("name", "1234567890")
                }.apply {
                    response = this
                }
            }
        }

        When("a GET request is made to the list endpoint with invalid parameters") {
            testApplication {
                client.get("/api/ancestry/list/{page}/{size}") {
                    parameter("page", 0)
                    parameter("limit", 0)
                }.apply {
                    response = this
                }
            }
        }
    }

    @Then("return a 404 status code")
    fun responseNotFound() {
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Then("return a 200 status code and send back the ancestry")
    fun responseOk() {
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("application/json", response.contentType().toString())
        val actualAncestry = CompletableDeferred<AncestryResponse>()
        TestScope().launch {
            actualAncestry.complete(
                Json.decodeFromString(AncestryResponse.serializer(), response.bodyAsText()),
            )
        }
        assertNotNull(actualAncestry)
    }

    @Then("return a 200 status code and send back the list of ancestries")
    fun responseList() {
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("application/json", response.contentType().toString())
        val actualAncestry = CompletableDeferred<List<AncestryResponse>>()
        TestScope().launch {
            actualAncestry.complete(
                Json.decodeFromString(ListSerializer(AncestryResponse.serializer()), response.bodyAsText()),
            )
        }
        assertNotNull(actualAncestry)
    }
}
