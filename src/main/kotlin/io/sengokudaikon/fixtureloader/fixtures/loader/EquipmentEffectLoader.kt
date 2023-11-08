package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.items.repository.EquipmentEffectRepository
import io.sengokudaikon.fixtureloader.fixtures.model.EquipmentEffectFixture

class EquipmentEffectLoader(private val equipmentEffectRepository: EquipmentEffectRepository) :
    FixtureLoader<EquipmentEffectFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<EquipmentEffectFixture> {
        TODO("Not yet implemented")
    }
}
