package io.sengokudaikon.isn.builder.fixtures.loader

import io.sengokudaikon.isn.builder.fixtures.model.HazardFixture
import io.sengokudaikon.isn.compendium.persistence.world.repository.HazardRepository

class HazardLoader(private val hazardRepository: HazardRepository) : FixtureLoader<HazardFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<HazardFixture> {
        TODO("Not yet implemented")
    }
}
