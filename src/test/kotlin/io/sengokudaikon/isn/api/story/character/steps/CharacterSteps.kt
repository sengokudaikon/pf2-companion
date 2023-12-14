package io.sengokudaikon.isn.api.story.character.steps

import io.cucumber.java8.En

class CharacterSteps : En {
    init {
        Given("the character creation API endpoint") {
        }
        When("I send a POST request to the endpoint") {
        }
        Then("return a 201 status code and send back the ID of the created character") {
            // Here check the responses
            // For example check status code and returned character ID
            // assertTrue(response.status == 201)
        }
        When("a POST request is made with missing parameters") {
            // Simulate making a POST request with missing parameters
        }

        Then("return a 400 status code with message about required parameters") {
            // Here check the responses
        }
    }
}
