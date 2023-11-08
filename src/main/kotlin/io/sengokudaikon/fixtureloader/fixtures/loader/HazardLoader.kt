package io.sengokudaikon.fixtureloader.fixtures.loader

import io.sengokudaikon.dbfinder.persistence.world.repository.HazardRepository
import io.sengokudaikon.fixtureloader.fixtures.model.HazardFixture

class HazardLoader(private val hazardRepository: HazardRepository) : FixtureLoader<HazardFixture> {
    override suspend fun insertIntoDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun setUpFixtures(): List<HazardFixture> {
        TODO("Not yet implemented")
    }
}
