package io.sengokudaikon.isn.api.story.compendium.steps

import io.kotest.core.spec.style.FunSpec
import io.ktor.client.request.*
import io.ktor.server.testing.*

class ApiEquipmentTests : FunSpec({
    test("testGetApiEquipmentArmorListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/armor/list")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentArmorNameName") {
        testApplication {
            val response = client.get("/api/equipment/armor")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/list")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentNameName") {
        testApplication {
            val response = client.get("/api/equipment")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentShieldListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/shield/list")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentShieldNameName") {
        testApplication {
            val response = client.get("/api/equipment/shield")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentWeaponListPageSize") {
        testApplication {
            val response = client.get("/api/equipment/weapon/list")
            // Add your assertions here
        }
    }

    test("testGetApiEquipmentWeaponNameName") {
        testApplication {
            val response = client.get("/api/equipment/weapon")
            // Add your assertions here
        }
    }
})