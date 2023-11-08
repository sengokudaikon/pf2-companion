package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.items.repository.EquipmentRepository
import io.sengokudaikon.fixtureloader.fixtures.model.EquipmentFixture

class EquipmentLoader(private val equipmentRepository: EquipmentRepository) : FixtureLoader<EquipmentFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<EquipmentFixture> {
        TODO("Not yet implemented")
    }
}
