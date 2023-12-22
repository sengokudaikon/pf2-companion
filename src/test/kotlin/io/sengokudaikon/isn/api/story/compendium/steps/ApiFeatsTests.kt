package io.sengokudaikon.isn.api.story.compendium.steps

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiFeatsTests : FunSpec({
    test("testGetApiFeatsGeneralId") {
        testApplication {
            val response = client.get("/api/feats/general/{id}")
            // Add your assertions here
        }
    }

    test("testGetApiFeatsGeneralListPageSize") {
        testApplication {
            val response = client.get("/api/feats/general/list/{page}/{size}")
            // Add your assertions here
        }
    }

    test("testGetApiFeatsGeneralNameName") {
        testApplication {
            val response = client.get("/api/feats/general/name/{name}")
            // Add your assertions here
        }
    }
})