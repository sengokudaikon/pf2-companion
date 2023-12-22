package io.sengokudaikon.isn.api.story.user

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.email
import io.kotest.property.arbitrary.string
import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json

class SuccessfulRegistrationTest : BehaviorSpec({
    Given("I am on the registration page") {
        testApplication {
            When("I enter valid credentials") {
                val response = client.post("/api/auth/register") {
                    setBody(Json {
                        "email" to Arb.email()
                        "username" to Arb.string(16)
                        "uid" to Arb.string(16)
                    })
                }

                Then("I should be registered and receive a success response") {
                    response.status.value shouldBe 200
                }
            }
        }
    }
})