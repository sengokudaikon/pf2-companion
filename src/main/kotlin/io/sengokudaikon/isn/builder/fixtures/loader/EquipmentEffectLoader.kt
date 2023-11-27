package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.EquipmentEffectFixture
import io.sengokudaikon.isn.compendium.persistence.items.repository.EquipmentEffectRepository

class EquipmentEffectLoader(private val equipmentEffectRepository: EquipmentEffectRepository) :
    FixtureLoader<EquipmentEffectFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<EquipmentEffectFixture> {
        TODO("Not yet implemented")
    }
}
