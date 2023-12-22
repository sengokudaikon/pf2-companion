package io.sengokudaikon.isn.api.story.compendium.steps

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiEquipmentTests : FunSpec({
    test("testGetApiEquipmentArmorListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/armor/list/{page}/{size}")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentArmorNameName") {
        testApplication {
            val response = client.get("/api/equipment/armor/name/{name}")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/list/{page}/{size}")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentNameName") {
        testApplication {
            val response = client.get("/api/equipment/name/{name}")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentShieldListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/shield/list/{page}/{size}")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentShieldNameName") {
        testApplication {
            val response = client.get("/api/equipment/shield/name/{name}")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentWeaponListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/weapon/list/{page}/{size}")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentWeaponNameName") {
        testApplication {
            val response = client.get("/api/equipment/weapon/name/{name}")
            // Add your assertions here
        }
    }
})