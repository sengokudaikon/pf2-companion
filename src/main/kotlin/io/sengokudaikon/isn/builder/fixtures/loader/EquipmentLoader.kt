package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.EquipmentFixture
import io.sengokudaikon.isn.compendium.persistence.items.repository.EquipmentRepository

class EquipmentLoader(private val equipmentRepository: EquipmentRepository) : FixtureLoader<EquipmentFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<EquipmentFixture> {
        TODO("Not yet implemented")
    }
}
